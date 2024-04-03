package org.example.common.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;


@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    public static int objCount = 0;
    private int id = ++objCount; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate = LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long health; //Значение поля должно быть больше 0
    private boolean loyal;
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private Chapter chapter; //Поле может быть null


    public SpaceMarine(String name, Coordinates coordinates, long health, boolean loyal, AstartesCategory category, Weapon weapon, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.health = health;
        this.loyal = loyal;
        this.category = category;
        this.weaponType = weapon;
        this.chapter = chapter;
    }

    public static SpaceMarine createPracticeObject() {
        SpaceMarine sp = new SpaceMarine(
                "The First",
                new Coordinates(1L, 2.0),
                23,
                true,
                AstartesCategory.HELIX,
                Weapon.BOLT_RIFLE,
                new Chapter("The first chapter", "Parents")
        );
        return sp;
    }

    @XmlElement(name = "creationDate")
    public String getCreationDate() {
        return creationDate.toString();
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (this.getName().compareTo(o.getName()) != 0) {
            return this.getName().compareTo(o.getName());
        } else if (this.getHealth() - o.getHealth() != 0) {
            return (int) (this.getHealth() - o.getHealth());
        } else if (this.getWeaponType().ordinal() - o.getWeaponType().ordinal() != 0) {
            return this.getWeaponType().ordinal() - o.getWeaponType().ordinal();
        }
        return this.id - o.id;
    }

    @XmlTransient
    public int getId() {
        return id;
    }
}
