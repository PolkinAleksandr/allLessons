package aleksandrpolkin.ru.hhlesson4;

public class DatasetFull {


    private String name;
    private String description;
    private int imageViewId;
    private boolean textWarning;

    public int getImageView() {
        return imageViewId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean getTextWarning(){ return textWarning;}

    public void setDatasetFull(String name, String description, int imageViewId, boolean textWarning){
        this.name = name;
        this.description = description;
        this.imageViewId = imageViewId;
        this.textWarning = textWarning;
    }

}
