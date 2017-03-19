package by.epam.movierating.bean;

import java.io.Serializable;

/**
 * Created by Даша on 25.01.2017.
 */
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
   // private String name_en;

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getName() {
        return name_en;
    }

    public void setName(String name_en) {
        this.name_en = name_en;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Genre genre = (Genre) o;

        if (id != genre.id) {
            return false;
        }
        return name != null ? name.equals(genre.name) : genre.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
