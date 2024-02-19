package mygame;

/**
 * @author BKNyoni
 * 
 * .
 */
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class voice implements Runnable {
    public static final String VOICE_ALAN ="alan";
    public static final String VOICE_KEVIN ="kevin";
    public static final String VOICE_KEVIN_16="kevin16";
    private Voice voice;
    private String words;
    public voice (String msg) {

        voice =VoiceManager.getInstance().getVoice(msg);
this.open();
    }
    public void addWords(String word){
    this.words =word;
    }
    public String getWords(){
    return this.words;
    }
    public void speak(String msg) {
        voice.speak(msg);
    }
    public void close(){
        voice.deallocate();
    }
    public void open(){
        voice.allocate();
    }
    @Override
    public void run(){
    this.speak(this.getWords());
    }
    
}

