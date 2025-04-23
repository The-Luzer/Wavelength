package wavelength;

public class Category {
    String min;
    String max;

    public Category(String min, String max){
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString(){
        return min + ", " + max;
    }
}
