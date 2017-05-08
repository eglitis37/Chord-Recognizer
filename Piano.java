import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * @author Klavs Eglitis
 * Teacher: Mr. Hardman
 * Lab#3, Piano
 * @version 24.04.2017
 */
public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]"};
    private String[] whiteNotes = {"3c","3d","3e","3f","3g","3a","3b","4c","4d","4e","4f","4g"};
    
    private String[] blackKeys = {"2","3","","5","6","7","","9","0","","="};
    private String[] blackNotes = {"3c#","3d#","","3f#","3g#","3a#","","4c#","4d#","","4f#"};
    
    private Key[] whiteKeyObjects = new Key [whiteKeys.length];
    private Key[] blackKeyObjects = new Key [blackKeys.length];
    private Key[] allKeyObjects = new Key [whiteKeys.length + blackKeys.length];
     
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }
    
    /**
     * makeKeys method make white and black keys and put them in the right order and size.
     * @param There are no parameters.
     * @return Nothings is returned.
     */
    private void makeKeys()
    {
        Key currentKey;
        
        for(int i = 0; i < whiteKeys.length; i++)
        {
            currentKey = new Key( whiteKeys[i], whiteNotes[i], "white-key", "white-key-down" );
            addObject( currentKey, (i*67)+30, 250);
            whiteKeyObjects[i] = currentKey;
        }
        
        for(int i = 0; i < blackKeys.length; i++)
        {
            if(blackKeys[i] !="")
            {
                currentKey = new Key( blackKeys[i], blackNotes[i], "black-key", "black-key-down" );
                addObject( currentKey, (i*67)+65, 195);
                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
            makeAllKeys();
                
        }
    }
    
    /**
     * makeAllKeys makes all keys in the world and spread them evenly.
     * @param There are no parameters.
     * @return Nothing is returned.
     */
    private void makeAllKeys()
    {
        for ( int i = 0; i < allKeyObjects.length; i++)
        {
            if( i % 2 == 0)
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }
            
            if( i % 2 != 0)
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }
        allKeyObjects[allKeyObjects.length -1] = whiteKeyObjects[whiteKeyObjects.length - 1];
    }
    
    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;
        

        int[] keyDownLocations = new int[20];
     
        for ( int i = 0; i < allKeyObjects.length; i++)
        {
            if(allKeyObjects[i] == null)
            {
                numNulls ++;
            }
            else
            {
                if (allKeyObjects[i].checkDown() == true )
                {
                    keyDownLocations[numAllDown] = i - numNulls;
                    numAllDown++;
                }
            }
        }
        
        if( numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);
            
        }
        else if( numAllDown == 3)
        {
            checkForTriads(keyDownLocations);
            
        }
        else if( numAllDown == 4)
        {
            checkForSevenths(keyDownLocations);
            
        }
        else
        {
            showText("", getWidth()/2, 50);
        }
        
    }
    
    /**
     * checkForSeconds checks if the right keys for Seconds are pressed and if is, shows that.
     * @param downKeys is an array that stores the locations of pressed keys.
     * @return Nothing is returned.
     */
    private void checkForSeconds(int[] downKeys)
    {
        if (downKeys[0] + 1 == downKeys[1] || downKeys[0] + 2 == downKeys[1] )
        {
           showText("You have made a second" , getWidth()/2, 50);
        }
    }
    
    /**
     * checkForTriads checks if the right keys for Triads are pressed and if is, shows that.
     * @param downKeys is an array that stores the locations of pressed keys.
     * @return Nothing is returned.
     */
    private void checkForTriads(int[] downKeys)
    {
        if (downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] ||
           downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] ||
           downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] )
           {
           showText("You have made a Triads" , getWidth()/2, 50);
        }
    }
    
    /**
     * checkForSevenths checks if the right keys for Sevenths are pressed and if is, shows that.
     * @param downKeys is an array that stores the locations of pressed keys.
     * @return Nothing is returned.
     */
    private void checkForSevenths(int[] downKeys)
    {
        if (downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 3 == downKeys[3]||
           downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 4 == downKeys[3]||
           downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3])
        {
           showText("You have made a Sevenths" , getWidth()/2, 50);
        }
    }
}    