package ModuleLesson6.Task1.Flowers;

/**
 * Created by Konfetka on 27.12.2015.
 */
public class BouquetOfFlowers {
    Rose rose;
    Chamomile chamomile;
    Aster aster;

    abstract class Flower extends IllegalStateException{}
    class Aster extends Flower{}
    class Rose  extends Flower{}
    class Chamomile extends Flower{}
    class Tulip extends Flower{}
}
