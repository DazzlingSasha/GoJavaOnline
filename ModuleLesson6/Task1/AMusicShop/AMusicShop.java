package ModuleLesson6.Task1.AMusicShop;


public class AMusicShop extends IllegalStateException{
    MusicalInstrument myGuitar;
    MusicalInstrument myPiano;
    MusicalInstrument myTrumpet;

    public AMusicShop(String message, Throwable cause) {
        super(message, cause);
    }

}
