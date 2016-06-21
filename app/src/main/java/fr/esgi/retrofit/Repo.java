package fr.esgi.retrofit;

/**
 * Created by Nico on 17/06/16.
 */
public class Repo {

    public String name;
    public String url;
    public String description;

    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getDescription() { return description; }

    public Repo(String name, String url, String description) {

        this.name = name;
        this.url = url;
        this.description = description;
    }
}
