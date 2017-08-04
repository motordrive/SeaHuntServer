package seahunt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String password;
    int progress[][] = new int[3][4];

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return password;
    }

    public void setProgress(int location, int riddle)
    {
        progress[location][riddle] = 1;
        System.out.println("set");
    }

    public String getProgress()
    {
        String p = "";
        for(int r = 0; r < 3; r++)
        {
            int num = 0;
            for(int c = 0; c < 4; c++)
            {
                num += progress[r][c];
            }
            p += num;
        }
        System.out.println("user get progress (p):" + p);
        return p;
    }

    public void resetProgress()
    {
        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 4; c++)
            {
                progress[r][c] = 0;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("User[user=%s]", name);
    }
}
