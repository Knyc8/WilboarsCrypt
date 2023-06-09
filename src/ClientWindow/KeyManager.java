package ClientWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    //VARIABLES
    private final SwingWindow sw;
    private boolean wPressed, sPressed, aPressed, dPressed, shootPressed;

    //CONSTRUCTOR
    public KeyManager(SwingWindow sw)
    {
        this.sw = sw;
    }


    //GETTERS
    public boolean isWPressed() {
        return wPressed;
    }
    public boolean isSPressed() {
        return sPressed;
    }
    public boolean isAPressed() {
        return aPressed;
    }
    public boolean isDPressed() {
        return dPressed;
    }
    public boolean isShootPressed(){return shootPressed;}


    //OTHER METHODS
    @Override
    public void keyTyped(KeyEvent e) {/*unused*/}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();  //gets user keystroke

        //TITLE SCREEN STATE
        if (sw.getGameState() == sw.getTITLE_SCREEN_STATE())
        {
            if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()-1);
                if (sw.getUi().getOptionNum() < 0)
                {
                    sw.getUi().setOptionNum(2);
                }
            }
            if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()+1);
                if (sw.getUi().getOptionNum() > 2)
                {
                    sw.getUi().setOptionNum(0);
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
                if (sw.getUi().getOptionNum() == 0)  //start game
                {
                    sw.setGameState(sw.getLOADING_STATE());
                }
                else if (sw.getUi().getOptionNum() == 1)  //load game
                {
                    sw.getFileManager().setInitiallyLoaded(sw.getFileManager().load());
                    sw.setGameState(sw.getLOAD_MENU_STATE());
                    keyCode = -1;
                    sw.getUi().setOptionNum(0);
                }
                else {
                    System.exit(0);  //exit game
                }
            }
        }

        //PLAY STATE
        if (sw.getGameState() != sw.getPAUSED_STATE()) {  //prevents inputs when paused or in title screen
            if (keyCode == KeyEvent.VK_W) {
                wPressed = true;
            }
            if (keyCode == KeyEvent.VK_S) {
                sPressed = true;
            }
            if (keyCode == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                dPressed = true;
            }
        }
        if (sw.getGameState() == sw.getPLAY_STATE()) {
            if (keyCode == KeyEvent.VK_J) {
                shootPressed = true;
            }
        }

        //PAUSED STATE
        if (sw.getGameState() == sw.getPAUSED_STATE()) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()-1);
                if (sw.getUi().getOptionNum() < 0)
                {
                    sw.getUi().setOptionNum(2);
                }
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()+1);
                if (sw.getUi().getOptionNum() > 2)
                {
                    sw.getUi().setOptionNum(0);
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
                if (sw.getUi().getOptionNum() == 0)
                {
                    sw.setGameState(sw.getTITLE_SCREEN_STATE());
                    sw.getFileManager().setInitiallyLoaded(false);
                    sw.getFileManager().setCurrentlyLoaded(false);
                }
                else if (sw.getUi().getOptionNum() == 1)  //save game
                {
                    sw.getFileManager().setAlreadySaved(sw.getFileManager().save());
                }
                else {
                    sw.setGameState(sw.getPLAY_STATE());
                }
            }
        }

        //Load Game Menu State
        if (sw.getGameState() == sw.getLOAD_MENU_STATE()) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()-1);
                if (sw.getUi().getOptionNum() < 0)
                {
                    sw.getUi().setOptionNum(1);
                }
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                sw.getUi().setOptionNum(sw.getUi().getOptionNum()+1);
                if (sw.getUi().getOptionNum() > 1)
                {
                    sw.getUi().setOptionNum(0);
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
                if (sw.getUi().getOptionNum() == 0)  //Load game
                {
                    if (sw.getFileManager().isInitiallyLoaded()) {
                        sw.setGameState(sw.getLOADING_STATE());
                    }
                }
                else {  //Return to TTS
                    sw.setGameState(sw.getTITLE_SCREEN_STATE());
                    sw.getUi().setOptionNum(0);
                    sw.getFileManager().setInitiallyLoaded(false);
                    sw.getFileManager().setCurrentlyLoaded(false);
                }
            }
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (sw.getGameState() == sw.getPLAY_STATE()) {
                sw.setGameState(sw.getPAUSED_STATE());
                sw.getUi().setOptionNum(0);
            }
            if (sw.getGameState() == sw.getLOSE_STATE()) {
                sw.setGameState(sw.getTITLE_SCREEN_STATE());
                sw.getUi().setOptionNum(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();  //gets user keystroke
        if (keyCode == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            dPressed = false;
        }
        if (keyCode == KeyEvent.VK_J) {
            shootPressed = false;
        }
    }
}
