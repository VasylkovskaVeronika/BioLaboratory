package Classes.Models;

import Classes.*;
import Classes.CustomGraphicsObjects.*;
import Classes.Threads.Animations.EnemyToLightOutAnimation;
import Classes.Threads.Animations.MoveAnimationThread;
import Classes.Threads.EnemyThreads.LightOutEnemyThread;
import Classes.Threads.GusBoosters.DoubleDonatesThread;
import Classes.Threads.EnemyThreads.FreezeEnemyThread;
import Classes.Threads.GusBoosters.SpeedUpThread;
import Classes.Views.FieldTableFrame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FieldTableModel extends AbstractTableModel {
    int rows;
    int columns;
    FieldTableFrame owner;
    List<Obstacle> obstacles= new ArrayList<>();
    List<Obstacle> way=new ArrayList<>();
    List<Obstacle> birds=new ArrayList<>();
    List<Obstacle> cars=new ArrayList<>();
    boolean isGameLost=false;
    private CustomCellModel[][] cells;
    private Point currentGusPos=new Point(0, 1);
    private Gus gus=new Gus();
    private Point currentEnemyPos=new Point(0, 5);
    private BlackOut enemy=new BlackOut();
    private List<CustomGraphicsSpace> replaceEnemyContent=new ArrayList<>();
    private Point currentrecpos=currentEnemyPos;
    private int goalSavedLives=0;

    public FieldTableModel(int r, int c) {
        rows=r;
        columns=c;
        generateObstaclesList();
        cells= new CustomCellModel[r][c];
        Arrays.stream(cells).forEach(cs-> {
            for(int i=0; i<c; i++)
                cs[i]=new CustomCellModel();
                });
        obstacles.stream().forEach(obstacle -> {
            CustomGraphicsObstacle obs=new CustomGraphicsObstacle();
            cells[obstacle.getRow()][obstacle.getColumn()]=new CustomCellModel();
            cells[obstacle.getRow()][obstacle.getColumn()].add(obs);
        });
//        Arrays.stream(cells).forEach(cs-> Arrays.stream(cs).filter(cell->!(cell.getContent() instanceof
//                        CustomGraphicsObstacle)).
//                forEach(cell->{
//                    //cell=new CustomCellModel();
//                    CustomGraphicsSpace s=new CustomGraphicsSpace();
//                    cell.add(s);
//                }));
        way.stream().forEach(f -> {
            CustomGraphicsSpace s=new CustomGraphicsSpace();
            cells[f.getRow()][f.getColumn()]=new CustomCellModel();
            cells[f.getRow()][f.getColumn()].add(s);
        });
        gus.setCurrentLevel(TotalRanking.getLastLevel());
        CustomGraffics gusP=new CustomGraffics();
            gusP.setSize(36, 36);
            gusP.setVisible(true);
        scatterBonuses();
        cells[currentGusPos.x][currentGusPos.y].add(gusP);
        CustomGraphicsBlackOut blackOut=new CustomGraphicsBlackOut(enemy);
        cells[currentEnemyPos.x][currentEnemyPos.y].add(blackOut);
        replaceEnemyContent.add(new CustomGraphicsSpace());
    }

    public void setOwner(FieldTableFrame owner) {
        this.owner = owner;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return JPanel.class;
    }


    public Object[][] getCells() {
        return cells;
    }

    public Gus getGus() {
        return gus;
    }

    public BlackOut getEnemy() {
        return enemy;
    }

    public boolean moveGusVertically(int toUp) {
        boolean isLevelUp=false;
        int newXPosition;
        if(currentGusPos.x+toUp==-1) {
            newXPosition=rows-1;
        }
        else if(currentGusPos.x+toUp==rows) {
            newXPosition=0;
        }
        else {
            newXPosition=currentGusPos.x+toUp;
        }
        if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsSpace) {
            CustomGraphicsSpace leftObj=new CustomGraphicsSpace();
            if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsDonate) {
                //logic
                gus.collectDonate();
                way.add(new Obstacle(newXPosition, currentGusPos.y));
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsDoubleDonate) {
                //logic
                gus.makeTakingDoubleDonuts();
                DoubleDonatesThread ddt=new DoubleDonatesThread(this);
                ddt.start();
                way.add(new Obstacle(newXPosition, currentGusPos.y));
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsUnmannedBird) {
                if(gus.getJarOfDonates()>=5) {
                    //logic
                    gus.buyBird();
                    Obstacle toRemove= birds.stream().filter
                            (obstacle -> {return obstacle.getRow()== newXPosition &&
                                    obstacle.getColumn()== currentGusPos.y;}).limit(1).toList().get(0);
                    birds.remove(toRemove);
                    way.add(new Obstacle(newXPosition, currentGusPos.y));
                }
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsSUV) {
                if(gus.getJarOfDonates()>=10) {
                    //logic
                    gus.buySUV();
                    Obstacle toRemove= cars.stream().filter
                            (obstacle -> {return obstacle.getRow()== newXPosition &&
                                    obstacle.getColumn()== currentGusPos.y;}).limit(1).toList().get(0);
                    cars.remove(toRemove);
                    way.add(new Obstacle(newXPosition, currentGusPos.y));
                }
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsFreezeUp) {
                enemy.makeFreeze();
                FreezeEnemyThread fet=new FreezeEnemyThread(this);
                fet.start();
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsSpeedUp) {
                gus.makeSpeedUp();
                SpeedUpThread sut=new SpeedUpThread(this);
                sut.start();
            }
            else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsNewHome) {
                int rand = (int)(Math.random() * 8-2+1) + 2; //saved family
                gus.saveFamily(rand);
            }
            //original condition (birds.size()+ cars.size())==0
            if(gus.getSavedLives()%2==0 && gus.getSavedLives()!=0) { //for quicker demonstration, 2 lives per level required
                gus.levelUp();
                isLevelUp=true;
            }
            if(birds.stream().filter(obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                    obstacle.getColumn()== currentGusPos.y;}).count()>0) {
                leftObj=new CustomGraphicsUnmannedBird();
            }
            if(cars.stream().filter(obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                    obstacle.getColumn()== currentGusPos.y;}).count()>0) {
                leftObj=new CustomGraphicsSUV();
            }

            //with animation, hopefully
            MoveAnimationThread moveGus=new MoveAnimationThread(cells[currentGusPos.x][currentGusPos.y],
                    cells[newXPosition][currentGusPos.y], owner, toUp>0? 2:3, leftObj, 1, this);
           moveGus.start();
            currentGusPos.x=newXPosition;
        }
        else if(cells[newXPosition][currentGusPos.y].getContent() instanceof CustomGraphicsBlackOut) {
            //visually
            EnemyToLightOutAnimation etloa=new EnemyToLightOutAnimation(owner, this);
            etloa.start();
            enemy.lightOn();
            //logically
            LightOutEnemyThread let=new LightOutEnemyThread(this, owner);
            let.start();
        }
        return isLevelUp;
    }
    public boolean moveGusHorizontally(int toRight) {
        boolean isLevelUp=false;
        int newYPosition;
        if(currentGusPos.y+toRight==-1) {
            newYPosition=columns-1;
        }
        else if(currentGusPos.y+toRight==columns) {
            newYPosition=0;
        }
        else {
            newYPosition=currentGusPos.y+toRight;
        }
        if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsSpace) {
            CustomGraphicsSpace leftObj=new CustomGraphicsSpace();
            //CustomGraffics movingHero;
            if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsDonate) {
                //logic
                gus.collectDonate();
                way.add(new Obstacle(currentGusPos.x, newYPosition));
            }
            else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsDoubleDonate) {
                gus.makeTakingDoubleDonuts();
                DoubleDonatesThread ddt=new DoubleDonatesThread(this);
                ddt.start();
                way.add(new Obstacle(currentGusPos.x, newYPosition));
            }
            else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsUnmannedBird) {
                if(gus.getJarOfDonates()>=5) {
                    //logic
                    gus.buyBird();
                    Obstacle toRemove= birds.stream().filter
                            (obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                            obstacle.getColumn()== newYPosition;}).limit(1).toList().get(0);
                    birds.remove(toRemove);
                    way.add(new Obstacle(currentGusPos.x, newYPosition));
                }
            }
            else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsSUV) {
                if(gus.getJarOfDonates()>=10) {
                    //logic
                    gus.buySUV();
                    Obstacle toRemove= cars.stream().filter
                            (obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                                    obstacle.getColumn()== newYPosition;}).limit(1).toList().get(0);
                    cars.remove(toRemove);
                    way.add(new Obstacle(currentGusPos.x, newYPosition));
                }
            }
            else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsFreezeUp) {
                enemy.makeFreeze();
                FreezeEnemyThread fet=new FreezeEnemyThread(this);
                fet.start();
            }
            else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsSpeedUp) {
                gus.makeSpeedUp();
                SpeedUpThread sut=new SpeedUpThread(this);
                sut.start();
            }
            //original condition (birds.size()+ cars.size())==0
            if(gus.getSavedLives()%2==0  && gus.getSavedLives()!=0) { //for quicker demonstration, 2 lives per level required
                gus.levelUp();
                isLevelUp=true;
            }
            if(birds.stream().filter(obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                    obstacle.getColumn()== currentGusPos.y;}).count()>0) {
                leftObj=new CustomGraphicsUnmannedBird();
            }
            if(cars.stream().filter(obstacle -> {return obstacle.getRow()== currentGusPos.x &&
                    obstacle.getColumn()== currentGusPos.y;}).count()>0) {
                leftObj=new CustomGraphicsSUV();
            }
            //with animation, hopefully
            MoveAnimationThread moveGus=new MoveAnimationThread(cells[currentGusPos.x][currentGusPos.y],
                    cells[currentGusPos.x][newYPosition], owner, toRight>0? 0:1, leftObj, 1, this);
            moveGus.start();
            currentGusPos.y=newYPosition;
        }
        else if(cells[currentGusPos.x][newYPosition].getContent() instanceof CustomGraphicsBlackOut) {
            //visually
            EnemyToLightOutAnimation etloa=new EnemyToLightOutAnimation(owner, this);
            etloa.start();
            //logically
            LightOutEnemyThread let=new LightOutEnemyThread(this, owner);
            let.start();
        }
        return isLevelUp;
    }

    public boolean moveEnemyVertically(int newXPosition, int direction, FieldTableFrame o){
        //meet with Gus
        if(cells[newXPosition][currentEnemyPos.y].getContent() instanceof CustomGraffics){
            isGameLost=true;
        }
        else if(cells[newXPosition][currentEnemyPos.y].getContent() instanceof CustomGraphicsSpace) {
            CustomGraphicsSpace r=(CustomGraphicsSpace) cells[newXPosition][currentEnemyPos.y].getContent();
            replaceEnemyContent.add(r);
            //with animation, hopefully
            MoveAnimationThread moveEnemy=new MoveAnimationThread(cells[currentEnemyPos.x][currentEnemyPos.y],
                    cells[newXPosition][currentEnemyPos.y], o, direction,
                    replaceEnemyContent.get(0), 0, this);
                moveEnemy.start();
            currentrecpos=new Point(newXPosition, currentEnemyPos.y);
            currentEnemyPos.x=newXPosition;
            replaceEnemyContent.clear();
            replaceEnemyContent.add(r);
        return true;
        }
        return false;
    }

    public boolean moveEnemyHorizontally(int newYPosition, int direction, FieldTableFrame o) {
        //meet with Gus
        if(cells[currentEnemyPos.x][newYPosition].getContent() instanceof CustomGraffics) {
            isGameLost=true;
        }
        else if(cells[currentEnemyPos.x][newYPosition].getContent() instanceof CustomGraphicsSpace) {
           CustomGraphicsSpace r=(CustomGraphicsSpace) cells[currentEnemyPos.x][newYPosition].getContent();
           replaceEnemyContent.add(r);
            //with animation, hopefully
            MoveAnimationThread moveEnemy=new MoveAnimationThread(cells[currentEnemyPos.x][currentEnemyPos.y],
                    cells[currentEnemyPos.x][newYPosition], o, direction,
                    replaceEnemyContent.get(0), 0, this);
            moveEnemy.start();
            currentEnemyPos.y=newYPosition;
           replaceEnemyContent.clear();
           replaceEnemyContent.add(r);
           currentrecpos=new Point(currentEnemyPos.x, newYPosition);
        return true;
        }
       return false;
    }

    public Point getCurrentEnemyPos() {
        return currentEnemyPos;
    }

    public boolean isGameLost() {
        return isGameLost;
    }

    public boolean isReplaceEnemyContentFree() {
        return (replaceEnemyContent.size()>0)? way.stream().filter(obstacle ->
        {return obstacle.getRow()== currentrecpos.x &&
                obstacle.getColumn()== currentrecpos.y;}).count()>0
                : false;
    }

    public void setReplaceEnemyContent(CustomGraphicsSpace replaceEnemyContent) {
        this.replaceEnemyContent.clear();
        this.replaceEnemyContent.add(replaceEnemyContent);
    }

    void generateObstaclesList() {
        //boolean upsideT=true;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                //Vertical obstacles |
                if ((j % 8 == 0 || (j-3) % 8 == 0 ||(j-6)%8==0) &&
                        (i%4==0 ||(i-1)%4==0 ||(i-2)%4==0)) { //|
                    obstacles.add(new Obstacle(i, j));
                }
//                //next row (move by 2)
//                if(((j-2) % 13 == 0|| (j-7) % 13 == 0 ||(j-12)%13==0)
//                        && (i%6==0 ||(i-1)%6==0 ||(i-2)%6==0 || (i-3)%6==0) && i!=0) { //|
//                    obstacles.add(new Obstacle(i, j));
//                }
                //Horizontal obstacles
                else if(((i-6)%8==0 || i%8==0) && ((j-2)%8==0 || (j-4)%8==0)) { //_
                    obstacles.add(new Obstacle(i, j));
                }
                else if(((i-2)%8==0 || (i-4)%8==0) && ((j-1)%8==0 || (j-5)%8==0)) {
                    obstacles.add(new Obstacle(i, j));
                }
                else {
                    way.add(new Obstacle(i, j));
                }
            }
        }
    }
    void scatterBonuses() {
        int donuts=0;
        boolean carTurn=false;
        for (var cell: way) {
            CustomGraphicsSpace s=new CustomGraphicsSpace();
            if(donuts>10 && carTurn) {
                s=new CustomGraphicsSUV();
                carTurn=false;
                donuts=0;
                cars.add(new Obstacle(cell.getRow(), cell.getColumn()));
                goalSavedLives++;
            }
            else if(donuts>5 && !carTurn) {
                s=new CustomGraphicsUnmannedBird();
                carTurn=true;
                donuts=0;
                birds.add(new Obstacle(cell.getRow(), cell.getColumn()));
                goalSavedLives++;
            }
            else {
                s=new CustomGraphicsDonate();
                donuts++;
            }
            cells[cell.getRow()][cell.getColumn()].add(s);
        }
        way.clear();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex].getContent();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        cells[rowIndex][columnIndex].add((Component) aValue);
    }
}
