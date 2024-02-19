package mygame;
import java.applet.Applet;
import java.applet.AudioClip; 
import java.net.URL;
import javax.swing.JOptionPane;
/**
 *
 * @author BKNyoni
 */
public class soundHandler
{
	AudioClip click,click2;
        private final String SOUND_PATH="res/sound/";
        
        String  [] GameClipsNames ={"cow.au","cow.au","donkey.wav","lion.wav",
                                    "lion2.wav","sheep.wav"};
        AudioClip [] GameClips =new AudioClip[GameClipsNames.length];

        URL urlClick,urlClick2;
	String wavefile,wavefile2;
	//sound enabler
        /**
         *
         */
        public boolean playFlag=true;
	
        /**
         *
         */
        public soundHandler()
	{
	 loadSound();
         //DynamicloadSound();
	}
	
        /**
         *
         * @param str_wave
         */
        public void setAudiofile(String str_wave)
	{
		wavefile= str_wave;
		//wavefile2="cow.au";
	}
        /**
         *
         * @return
         */
        public  boolean getSoundFlag()
	{
		return playFlag;
		}
        /**
         *
         */
        public void loadSound()
	 {
	  try
	  {
	  if(this.wavefile==null && this.wavefile2==null)
	    {
             urlClick = soundHandler.class.getResource(SOUND_PATH+"bottle.wav");
             urlClick2 = soundHandler.class.getResource(SOUND_PATH+"cow.au");
              }
           click = Applet.newAudioClip(urlClick);
           click2 = Applet.newAudioClip(urlClick2);
	   }
	   catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Sound files not found.\nfilename is "+urlClick,"Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
	   catch(Exception e)
	   {}			
	  }
       public void DynamicloadSound()
	{
            for(int pointer=0; pointer< GameClips.length; ++pointer)
                 {
                     try
                      {
                   urlClick = soundHandler.class.getResource(SOUND_PATH+GameClipsNames[pointer]);
                   GameClips[pointer] = Applet.newAudioClip(urlClick);
                   urlClick=null;
                     }
                    catch(NullPointerException e)
                     {
                     JOptionPane.showMessageDialog(null,"Sound files not found.\nfilename is "+urlClick,"Game Resource Error",
                     JOptionPane.ERROR_MESSAGE);
                       System.exit(1);
                    }
                     catch(Exception e) {}
                       }
         }

        /**
         *
         * @param enableDisable
         */
        public void ConfigSound(boolean enableDisable)
	{
		this.playFlag=enableDisable;
		//System.out.print(playFlag);
		}
	
        /**
         *
         */
        public void addEffect()
	{
		if(playFlag)
		{
                 click.play();
                } 
	 }
        /**
         *
         */
          public void addCowEffect()
	   {
		if(playFlag)
		{
		 //int cSound = (int)(GameClips.length*Math.random() + 1);
		 //GameClips[cSound].play();
		 click2.play();
                }
	     }
	}