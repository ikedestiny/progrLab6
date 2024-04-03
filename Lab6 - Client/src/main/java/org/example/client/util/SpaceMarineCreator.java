package org.example.client.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.data.*;
import org.example.common.exception.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class is used to collect inputs and creates a space marine object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceMarineCreator {
    private Scanner scanner = new Scanner(System.in);
    private Scanner scanner1 = new Scanner(System.in);

    /**
     * @return spacemarine
     */
    public SpaceMarine createSpaceMarine() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        SpaceMarine sp = null;
        try {
            String name;
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
            while (name == null) {
                System.out.print("Name cannot be null, Enter again: ");
                name = scanner.nextLine();
            }
            Long x;
            Double y;
            long health;
            boolean loyal;
            Chapter chapter;
            Coordinates coordinates;
            AstartesCategory category;
            Weapon weapon;
            System.out.print("Enter x: ");
            x = scanner.nextLong();
            while (scanner.nextLine() == null || x < -286) {
                System.out.print("X cannot be null");
                x = scanner.nextLong();
            }

            System.out.print("Enter y: ");
            y = scanner.nextDouble();
            while (scanner.nextLine() == null || y > 703) {
                System.out.print("Y cannot be null");
                y = scanner.nextDouble();
            }

            System.out.print("Enter Health: ");
            health = scanner.nextLong();
            while (scanner.nextLine() == null || health < 1) {
                System.out.print("health cannot be null");
                health = scanner.nextLong();
            }


            coordinates = new Coordinates(x, y);


            System.out.print("Enter loyal: ");
            var bool = scanner.nextLine();
            while (bool == null) {
                System.out.print("Loyal cannot be null");
                bool = scanner.nextLine();
            }
            loyal = Boolean.parseBoolean(bool);


            Integer value = null;
            System.out.println("""
                    1. ASSAULT
                    2. INCEPTOR
                    3. LIBRARIAN
                    4. HELIX
                    5. APOTHECARY
                    """);
            System.out.print("Enter one of the numbers(Cannot be null): ");
            while (value == null) {
                value = scanner.nextInt();
            }
            category = switch (value) {
                case 1 -> AstartesCategory.ASSAULT;
                case 2 -> AstartesCategory.INCEPTOR;
                case 3 -> AstartesCategory.LIBRARIAN;
                case 4 -> AstartesCategory.HELIX;
                case 5 -> AstartesCategory.APOTHECARY;
                default -> throw new InvalidInputException();

            };


            System.out.println("""
                    1. HEAVY_BOLTGUN
                    2. BOLT_RIFLE
                    3. GRAV_GUN
                    4. HEAVY_FLAMMER
                    """);
            System.out.print("Enter one of the numbers: ");
            int valueFor = scanner.nextInt();
            weapon = switch (valueFor) {
                case 1 -> Weapon.HEAVY_BOLTGUN;
                case 2 -> Weapon.BOLT_RIFLE;
                case 3 -> Weapon.GRAV_GUN;
                case 4 -> Weapon.HEAVY_FLAMER;
                default -> throw new InvalidInputException();
            };


            System.out.print("Enter Chapter name: ");
            String nameOfChapter = scanner.next();

            String parent = nameOfChapter + " parent";

            chapter = new Chapter(nameOfChapter, parent);
            if (!(name == null || coordinates == null || category == null || weapon == null || chapter == null)) {
                sp = new SpaceMarine(name, coordinates, health, loyal, category, weapon, chapter);
            }
        } catch (InputMismatchException | InvalidInputException e) {
            System.out.println("Invalid input. Work with the collection will be finished");
        }
        return sp;
    }


    public int getId() {
        System.out.println("Enter Id:");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong format input....");
            return 0;
        }
    }
} 

