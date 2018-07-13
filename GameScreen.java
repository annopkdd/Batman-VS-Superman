import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.sound.sampled.*;
import sun.audio.*; 
import java.io.*;
import java.net.URL;
public class GameScreen extends JPanel implements ActionListener, KeyListener {
    public static final int KEY_UP = 0;
    public static final int KEY_DOWN = 1;
    public static final int KEY_LEFT = 2;
    public static final int KEY_RIGHT = 3;
    public static final int KEY_FIRE = 4;
    public static final int KEY_ENTER = 5;

    private boolean[] keyStates = new boolean[6];
    private Bgmove bgmove;
    private Bgmove2 bgmove2;
    private Bgstatic bgend;

    private Bgstatic bgboss1;
    private Bgstatic bgboss2;
    private Bgstatic bgboss3;

    private boolean  spawnboss1 = false;
    private boolean  spawnboss2 = false;
    private boolean  spawnboss3 = false;

    private boolean  spawnmon1 = false;
    private boolean  spawnmon2 = true;
    private boolean  spawnmon3 = false;

    private boolean  state1 = true;
    private boolean  state2 = true;
    private boolean  state3 = true;

    private boolean start = true;
    private boolean status = true;
    private boolean endgame = false;

    private boolean[] statusSound = {false,false,false,false,false,false,false,false,false};
   
    private Clip[] sound = new Clip[9];

    private Boss1 boss1;
    private Boss2 boss2;
    private Boss3 boss3;

    private Mychar mychar;
    private Font font;
    private int state=1;
    private int countbullet=0;
    private Timer timer ;



     private ArrayList<GameObject> lasor = new ArrayList<GameObject>();
     private ArrayList<GameObject> bullets = new ArrayList<GameObject>();
     private ArrayList<GameObject> enemies = new ArrayList<GameObject>();
     private ArrayList<GameObject> boss = new ArrayList<GameObject>();
     private Startscreen startscreen ;
     private Random random;

    private int score = 0;

    public GameScreen() {
        random = new Random();
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        font = new Font ( "Tw Cen MT", 5, 30 );


        ImageIcon loader = new ImageIcon(getClass().getResource("pic/skydark1.png"));
        Image bg1 = loader.getImage();

        loader = new ImageIcon(getClass().getResource("pic/skydark2.png"));
        Image bg2 = loader.getImage();



        bgmove = new Bgmove(this,bg1);
        bgmove2 = new Bgmove2(this,bg2);

        boss1 = new Boss1(this,2500,200);
        boss2 = new Boss2(this,2500,200);
        boss3 = new Boss3(this,2500,50);




        mychar = new Mychar(this,300,300);
      
   
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(1000/50, this);
        timer.start();
    }

    public void update(){
            if(start){
                if(status){
                    if(!statusSound[0]){
                        URL url = this.getClass().getClassLoader().getResource("sound/mainwav.wav");
                        playSound(url,0);
                        statusSound[0] = true;
                    }
                
                   
                    ImageIcon loader = new ImageIcon(getClass().getResource("pic/start.png"));
                    Image bg1 = loader.getImage();
                    startscreen = new Startscreen(this,bg1);
                    if(getKeyState(5)){
                        start = false;

                    }
                }
                else{
        
                   
                    ImageIcon loader = new ImageIcon(getClass().getResource("pic/dead.png"));
                    Image bg1 = loader.getImage();
                    startscreen = new Startscreen(this,bg1);
                    if(getKeyState(5)){
                    
                        state  = 1;
                        boss1 = new Boss1(this,2500,200);
                        boss2 = new Boss2(this,2500,200);
                        boss3 = new Boss3(this,2500,50);
                        mychar = new Mychar(this,300,300);
                        score = 0;
                        countbullet=0;
                        lasor.clear();
                        bullets.clear();
                        enemies.clear();
                        boss.clear();
                      
                        loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
                        bg1 = loader.getImage();
                        loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
                        Image bg2 = loader.getImage();
                        bgmove = new Bgmove(this,bg1);
                        bgmove2 = new Bgmove2(this,bg2);
                        spawnboss1 = false;
                        spawnboss2 = false;
                        spawnboss3 = false;

                        spawnmon1 = false;
                        spawnmon2 = true;
                        spawnmon3 = false;

                        state1 = true;
                        state2 = true;
                        state3 = true;                       


                        timer.start();
                        start = false;
                        status = true;                  
                    }
                }

            }
            else {  
                    //status = true;
                    
                    
                    ArrayList<GameObject> deadList = new ArrayList<GameObject>();
                    mychar.update();

                    countbullet = countbullet+1;
                
                    if(countbullet >300){   /// COUNT BULLET FOR RANDOM SHOOT
                        countbullet =0;
                    }
                    if( mychar.getHP()<=0  ){ //  HP = 0  SUPERMAN DEAD
                            start = true;
                            status = false;
                            stopSound();
                            URL url = this.getClass().getClassLoader().getResource("sound/deadwav.wav");
                            playSound(url,7);
                    }


             
                    if(state == 1 ){
                        if( state1 ){
                            stopSound();
                            URL url = this.getClass().getClassLoader().getResource("sound/stage1wav.wav");
                            playSound(url,1);

                            ImageIcon loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
                            Image bg1 = loader.getImage();

                            loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
                            Image bg2 = loader.getImage();


                            bgmove = new Bgmove(this,bg1);
                            bgmove2 = new Bgmove2(this,bg2);
                            state1 = false;
                        }
                        if( score >= 1500 && enemies.size()==0 && boss.contains(boss1)==false ){
                          
                            ImageIcon loader = new ImageIcon(getClass().getResource("pic/skycon.png"));
                            Image bgstatic = loader.getImage();

                            bgboss1 = new Bgstatic(this,bgstatic);
                            boss1 = new Boss1(this,2500,200);
                            boss.add(boss1);
                            spawnboss1 = true;

                            stopSound();
                            URL url = this.getClass().getClassLoader().getResource("sound/boss1wav.wav");
                            playSound(url,4);

                        }
                        else if(enemies.size()<=5 && spawnboss1==false && score <1500){
                            
                            int x = random.nextInt(3)+1;
                            if(x==1){
                                spawnsmon1vs1();
                            }
                            else if(x==2){
                                spawnsmon1vs2();
                            }

                            sound[1].loop(100);
                            
                        }
                        
                    }
                    else if(state == 2){
                        if(state2){
                                
                                ImageIcon loader = new ImageIcon(getClass().getResource("pic/skydark1.png"));
                                Image bg1 = loader.getImage();

                                loader = new ImageIcon(getClass().getResource("pic/skydark2.png"));
                                Image bg2 = loader.getImage();



                                bgmove = new Bgmove(this,bg1);
                                bgmove2 = new Bgmove2(this,bg2);
                                state2 = false;

                                stopSound();
                                URL url = this.getClass().getClassLoader().getResource("sound/stage2wav.wav");
                                playSound(url,2);

                        }
                        if(score >=3000 && enemies.size()==0 && boss.contains(boss2)==false) {
                               
                                ImageIcon loader = new ImageIcon(getClass().getResource("pic/skydark1.png"));
                                Image bgstatic = loader.getImage();

                                bgboss2 = new Bgstatic(this,bgstatic);  
                                boss2 = new Boss2(this,2500,200);
                                boss.add(boss2);

                                spawnboss2 = true;
                               

                                stopSound();
                                URL url = this.getClass().getClassLoader().getResource("sound/boss2wav.wav");
                                playSound(url,5);
                        }
                        else if(enemies.size()<=5  && spawnboss2==false && score <3000){
                             int x = random.nextInt(4)+1;
                             if(x==1){
                                spawnsmon2();
                             }
                             if(x==2){
                                spawnsmon1vs1();
                                spawnsmon2();
                             }
                             if(x==3){
                                spawnsmon1vs2();
                                spawnsmon2();
                             }
                             if(x==4){
                                spawnsmon1vs1();
                                spawnsmon1vs2();
                             }
                             sound[2].loop(100);
                            
                        }
                    }
                    else if(state == 3){
                     
                        if(state3){
                                
                                ImageIcon loader = new ImageIcon(getClass().getResource("pic/skyblack1.png"));
                                Image bg1 = loader.getImage();

                                loader = new ImageIcon(getClass().getResource("pic/skyblack2.png"));
                                Image bg2 = loader.getImage();



                                bgmove = new Bgmove(this,bg1);
                                bgmove2 = new Bgmove2(this,bg2);

                                state3 = false;

                                stopSound();
                                URL url = this.getClass().getClassLoader().getResource("sound/stage3wav.wav");
                                playSound(url,3);

                        }
                        if(score >=6000 && enemies.size()==0 && boss.contains(boss3)==false) {
                                
                                ImageIcon loader = new ImageIcon(getClass().getResource("pic/skyblack1.png"));
                                Image bgstatic = loader.getImage();


                                bgboss3 = new Bgstatic(this,bgstatic);
                                boss3 = new Boss3(this,2500,50);
                                boss.add(boss3);
                                spawnboss3 = true;
                            
                                stopSound();
                                URL url = this.getClass().getClassLoader().getResource("sound/boss3wav.wav");
                                playSound(url,6);
                        }
                        else if(enemies.size()<=5 && spawnboss3==false && score <6000){
                                
                                 int x = random.nextInt(5)+1;
                                 if(x==1){
                                    spawnsmon3();
                                 }
                                 if(x==2){
                                    spawnsmon1vs1();
                                    spawnsmon3();
                                 }
                                 if(x==3){
                                    spawnsmon1vs2();
                                    spawnsmon3();
                                 }
                                 if(x==4){
                                    spawnsmon3();
                                    spawnsmon2();
                                 }
                                 if(x==5){
                                    spawnsmon3();
                                    spawnsmon1vs2();
                                    spawnsmon1vs1();

                                sound[3].loop(100);

                             }


                        }
                    }
                    bgmove2.update();
                    bgmove.update();

                        
                  
                       
            //===========================================================================================
                    
                    if(state == 1){

                        if( boss1.update() == false  ){  // BOSS1 DEAD
                            boss1dead();
                            deadList.add(boss1);
                            spawnboss1 = false;     
                            state = 2;

                            stopSound();
                            stageclear();
                        }
                        if(spawnboss1){
                            sound[4].loop(100);
                        }
                    }
                        
                    else if(state == 2){
                        if(boss2.update() == false ){  // BOSS2 DEAD
                            
                            deadList.add(boss2);
                            spawnboss2 = false;
                            state = 3;

                            stopSound();
                            stageclear();
                        }
                        if(spawnboss1){
                            sound[5].loop(100);
                        }

                    }
                    else if(state == 3){
                        if(boss3.update() == false ){  // BOSS3 DEAD
                            boss3dead();
                            ImageIcon loader = new ImageIcon(getClass().getResource("pic/endgame.png"));
                            Image endgames = loader.getImage();
                            startscreen = new Startscreen(this,endgames);

                            deadList.add(boss3);
                            spawnboss3 = false;
                            endgame = true;
                            start = true;
                            stopSound();
                            stageclear();
                            URL url = this.getClass().getClassLoader().getResource("sound/endgame.wav");
                            playSound(url,0);
                            

                        }
                        if(spawnboss1){
                            sound[6].loop(100);
                        }

                    }        
                   


                    for(GameObject object : boss){ // OBJECT BOSS
                        object.update();
                       

                    }




                    for(GameObject object : lasor){           /////////////// OBJECT LASOR
                        if (object.update() == false) {
                            deadList.add(object);
                        }
                        for (GameObject enemy : enemies) {   
                               if(object.collidedWith(enemy)){
                                soundcoliedlasor();
                                    //System.out.println("Hp monter before = "+enemy.getHP());
                                    enemy.setHP(enemy.getHP()-1);
                                    //System.out.println("Hp monter after = "+enemy.getHP());
                                    deadList.add(object);
                                }
                        }
                        for(GameObject bosses : boss){
                            if(object.collidedWith(bosses)){
                                soundcoliedlasor();
                                bosses.setHP(bosses.getHP()-1);  
                                deadList.add(object);
                            }

                        }        
                        
                    }    /////////////// OBJECT LASOR

                    for(GameObject object : bullets){  /////////////// OBJECT BULLET
                        if (object.update() == false) {
                            deadList.add(object);
                        }
                        if( mychar.collidedWith(object)){
                            mychar.setHP(mychar.getHP()-1);
                            deadList.add(object);
                        }
                    }        /////////////// OBJECT BULLET

                    for(GameObject object : boss){    //// BOSS MOVE TO SUPERMAN
                        if(object.collidedWith(mychar)){
                            mychar.setHP(0);
                        }
                    }

                    for(GameObject object : enemies){ /////////////// OBJECT ENEMIES
                       // System.out.println("Hp monter = "+object.getHP());
                        if(object.getCount()<=-25){

                            soundenemydead();
                            if(object instanceof Minibat2){
                                score += 10;    
                            }
                            else if(object instanceof Minibat){
                                score += 20; 
                            }
                            else if(object instanceof Minibat3){
                                score += 100; 
                            }
                            deadList.add(object);                   
                                 
                        }
                        else if(object.update() == false){
                            deadList.add(object);
                        }
                        if( mychar.collidedWith(object)){
                            soundcolied();
                            mychar.setHP(mychar.getHP()-1);
                            deadList.add(object);
                        }

                        object.update();
                    }       /////////////// OBJECT ENEMIES
                    
                    for(int i=0;i<enemies.size();i++){  /////////////// ENEMY SHOOT BULEET
                        if(countbullet % 150 ==0 && ( enemies.get(i) instanceof Minibat && spawnboss3 == false 
                            )){
                            Bullet bullet = new Bullet(this,enemies.get(i).getX(),enemies.get(i).getY());
                            bullets.add(bullet);
                        }

                        if(countbullet == 150 ) {
                            if(i%2 ==0){
                                if( enemies.get(i) instanceof Minibat && spawnboss3 == false){
                                     Bullet bullet = new Bullet(this,enemies.get(i).getX(),enemies.get(i).getY());
                                    bullets.add(bullet);

                                }
               
                            }
                        }
                        else if(countbullet ==300 ){
                            if(i%2!=0){
                                if( enemies.get(i) instanceof Minibat && spawnboss3 == false ){
                                    Bullet bullet = new Bullet(this,enemies.get(i).getX(),enemies.get(i).getY());
                                    bullets.add(bullet);
                                }

                            }
                        }
                    }               /////////////// ENEMY SHOOT BULEET
                    if( spawnboss2) //////// BOSS 2 SHOOT
                    {
                        if(countbullet%15 == 0 ){
                            Bullet bullet = new Bullet(this,boss2.getX(),boss2.getY());
                            bullets.add(bullet);
                            bullet = new Bullet(this,boss2.getX()-20,boss2.getY());
                            bullets.add(bullet);
                            bullet = new Bullet(this,boss2.getX()-40,boss2.getY());

                            bullets.add(bullet);
                            bullet = new Bullet(this,boss2.getX(),boss2.getY()+20);
                            bullets.add(bullet);
                            bullet = new Bullet(this,boss2.getX()-20,boss2.getY()+20);
                            bullets.add(bullet);
                            bullet = new Bullet(this,boss2.getX()-40,boss2.getY()+20);
                            bullets.add(bullet);
                        }

                    } //// BOSS 2 SHOOT

                    if( spawnboss3) //////// BOSS 3 SHOOT
                    {
                            int randomy = random.nextInt(950);
                            if(countbullet%15 == 0 ){
                                Bullet bullet = new Bullet(this,boss3.getX(),randomy);              
                                bullets.add(bullet);
                                bullet = new Bullet(this,boss3.getX()-20,randomy);
                                bullets.add(bullet);

                                bullet = new Bullet(this,boss3.getX(),randomy);
                                bullets.add(bullet);
                                bullet = new Bullet(this,boss3.getX()-20,randomy);
                                bullets.add(bullet);


                                bullet = new Bullet(this,boss3.getX(),randomy);              
                                bullets.add(bullet);
                                bullet = new Bullet(this,boss3.getX()-20,randomy);
                                bullets.add(bullet);

                                bullet = new Bullet(this,boss3.getX(),randomy);
                                bullets.add(bullet);
                                bullet = new Bullet(this,boss3.getX()-20,randomy);
                                bullets.add(bullet);

                            }
                            if(countbullet==150){
                                spawnsmon2();
                            }
                    }


                    //// BOSS 3 SHOOT



                    for (GameObject deadObject : deadList) {      /////////////// DELETE DEAD OBJECT
                        if( enemies.contains(deadObject) ){

                            enemies.remove(deadObject);
                            
                        }
                        if( lasor.contains(deadObject) ){
                            lasor.remove(deadObject);
                        }
                        if(bullets.contains(deadObject)){
                            bullets.remove(deadObject);
                        }
                        if( boss.contains(deadObject)  ){
                            boss.remove(deadObject);
                        }


                    }             /////////////// DELETE DEADOBJECT
                }

        repaint();
    }
    
    public void spawnsmon1vs1(){
            int x=2500;int y=500;
        

            for(int i=0;i<5;i++){
                    Minibat2 minibat = new Minibat2(this,x,y); 
                    enemies.add(minibat);
                    x=x+65;
                    y=y+110;
                
            }   
             x=2565;y=390;
            for(int i=0;i<4;i++){
                    Minibat2 minibat = new Minibat2(this,x,y); 
                    enemies.add(minibat);
                    x=x+65;
                    y=y-110;
                
            }  


    }
    public void spawnsmon1vs2(){
            int x=2400;int y=200;
           
            for(int i=0;i<5;i++){
                    Minibat2 minibat = new Minibat2(this,x,y); 
                    enemies.add(minibat);
                    x=x+120;
                
            }   
             x=2400;y=800;
            for(int i=0;i<5;i++){
                    Minibat2 minibat = new Minibat2(this,x,y); 
                    enemies.add(minibat);
                    x=x+120;
                
            }  
            x=2400;y=500;
            for(int i=0;i<5;i++){
                     Minibat2 minibat = new Minibat2(this,x,y); 
                    enemies.add(minibat);
                    x=x+120;
                
            }  


    }
    public void spawnsmon2(){
            spawnbat();
            spawnbat();
            Minibat minibat = new Minibat(this,2500,10); 
            enemies.add(minibat);
            minibat = new Minibat(this,2500,260); 
            enemies.add(minibat);
            minibat = new Minibat(this,2500,550); 
            enemies.add(minibat);
            minibat = new Minibat(this,2500,760); 
            enemies.add(minibat);

            minibat = new Minibat(this,3000,10); 
            enemies.add(minibat);
            minibat = new Minibat(this,3000,260); 
            enemies.add(minibat);
            minibat = new Minibat(this,3000,550); 
            enemies.add(minibat);
            minibat = new Minibat(this,3000,760); 
            enemies.add(minibat);
     
    }
        public void spawnsmon3(){

            Minibat3 minibat = new Minibat3(this,2300,200); 
            enemies.add(minibat);
            minibat = new Minibat3(this,2500,500); 
            enemies.add(minibat);
            minibat = new Minibat3(this,2300,800); 
            enemies.add(minibat);

           
            
    }   

    public void playSound(URL url,int i){
        try {
           // Open an audio input stream.
           AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
           sound[i] = AudioSystem.getClip();
           sound[i].open(audioIn);
           sound[i].start();
           statusSound[i] = true;


          /// Mp3Player();
        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void stopSound(){
        for(int i=0;i<9;i++){
            if(statusSound[i]){
                sound[i].stop(); 
                statusSound[i] = false;
            }
        }        
    }
    public void soundLasor(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/lasor.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip lasorsound = AudioSystem.getClip();

            lasorsound.open(audioIn);
            lasorsound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void soundenemydead(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/enemydead.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip enemydead = AudioSystem.getClip();

            enemydead.open(audioIn);
            enemydead.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void soundcolied(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/colied.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void soundcoliedlasor(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/coliedlasor.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void boss1dead(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/boss1dead.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void boss3dead(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/boss3dead.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void spawnbat(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/spawnbat.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    public void stageclear(){
        try {
     
            URL url = this.getClass().getClassLoader().getResource("sound/stagepassed.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
  
            Clip sound = AudioSystem.getClip();

            sound.open(audioIn);
            sound.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    
    
    
    
 
    
 



    @Override
    public void paintComponent(Graphics g) {
        if(start){
            super.paintComponent(g);
            startscreen.draw(g);
            if(endgame){
                sound[0].loop(100);
                timer.stop();
            }

        }
        else{

                super.paintComponent(g);
                bgmove.draw(g);
                bgmove2.draw(g);
                
                g.setColor(Color.BLACK);
                g.setFont ( font );
                g.drawString("Score: " + score, 10, 30);
                if( spawnboss1 ){
                    bgboss1.draw(g);
                    if(boss1.getHP()<0){
                        g.drawString("HP BOSS : " +0, 1500, 30);
                    }
                    else{
                        g.drawString("HP BOSS : " + boss1.getHP(), 1500, 30);
                    }

                    
                } 
                else if(spawnboss2 ){
                    bgboss2.draw(g);
                    if(boss2.getHP()<0){
                         g.drawString("HP BOSS : " +0, 1500, 30);
                     
                    }
                    else{
                        g.drawString("HP BOSS : " + boss2.getHP(), 1500, 30);
                    }

                    
                }
                else if(spawnboss3 ){
                    bgboss3.draw(g);
                    if(boss3.getHP()<0){
                         g.drawString("HP BOSS : " +0, 1500, 30);
                     
                    }
                    else{
                        g.drawString("HP BOSS : " + boss3.getHP(), 1500, 30);
                    }

                    
                }
                mychar.draw(g); 
               
                if( mychar.getHP() <0){
                    g.drawString("HP : " +0 , 250, 30);
                }
                else{
                    g.drawString("HP : " + mychar.getHP(), 250, 30);
                }
                
                for(GameObject object : boss){
                    object.draw(g);
                }

                 for (GameObject object : lasor) {
                    object.draw(g);
                }


                for(GameObject object : enemies){
                    object.draw(g);
                }

                for(GameObject object : bullets){
                    object.draw(g);
                }
            }
       

       
    }

     @Override
    public void actionPerformed(ActionEvent e) {
         update();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    public boolean getKeyState(int key) {
        return keyStates[key];
    }

        @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = true;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = true;
                break;
            case KeyEvent.VK_ENTER:
                keyStates[KEY_ENTER] = true;
                break;
            case KeyEvent.VK_SPACE   :
                keyStates[KEY_FIRE] = true;
                    soundLasor();
                    Lasor lasors = new Lasor(this, mychar.getX()+225, mychar.getY()+16);
                    lasor.add(lasors);
             

                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = false;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = false;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = false;
                break;
            case KeyEvent.VK_ENTER:
                keyStates[KEY_ENTER] = false;
                break;
            case KeyEvent.VK_SPACE:
                keyStates[KEY_FIRE] = false;
                break;
        }
    }
    


}
