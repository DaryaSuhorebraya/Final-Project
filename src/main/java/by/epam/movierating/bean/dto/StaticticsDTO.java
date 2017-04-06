package by.epam.movierating.bean.dto;

/**
 * Created by Даша on 04.04.2017.
 */
public class StaticticsDTO {
    private String label;
    private int value;


    public StaticticsDTO() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
