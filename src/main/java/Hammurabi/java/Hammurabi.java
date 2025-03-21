
package hammurabi;               // package declaration
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);

    public static void main(String\[\]args) { // required in every Java program
        new Hammurabi().playGame();
    }


    //create new hammurabi game object
    void playGame() {
        int year = 1;
        int starvedPeople = 0;
        int immigrants = 0;
        int population = 100;
        int bushels = 2800;
        int acresOwned = 1000;
        int price = 19; // bushels/acre

    }


    //display initial game state
    printSummary();
    //game loop for 10years
            for(year =1;year <=10;year++)

    {
        int acresToBuy = askHowManyAcresToBuy(price, bushels);
        if (acresToBuy > 0) {
            bushels -= acresToBuy * price;
            acresOwned -= acresToBuy;
        } else {
            int acresToSell = askHowManyAcresToSell(acresOwned);
            if (acresToSell > 0) {
                bushels += acresToSell * price;
                acresOwned -= acresToSell;
            }
        } //feed the people
        int bushelsFedToPeople = askHowMuchGrainToFeedPeople(bushels);
        bushelsFedToPeople -= bushels;
        //plant crops
        int acresToPlant = askHowManyAcresToPlant(acresOwned, population, bushels);
        bushels -= acresToPlant;
        //parallel events
        int plagueDeaths = plagueDeaths(population);
        population -= plagueDeaths;
        starvedPeople = starvationDeaths(population, bushelsFedToPeople);
        population -= starvedPeople;
        //check for uprising
        if (uprising(population, starvedPeople)) {
            System.out.println("true");
            finalSummary();
            return;
        } //calculate immigrants
        if (starvedPeople == 0) {
            immigrants = immigrants(population, acresOwned, bushels);
            population += immigrants;
        } else {
            immigrants = 0;
        } //harvest crops
        int harvest = harvest(acresToPlant, acresToPlant);
        bushels += harvest;
        //rats eat grain
        int ratsEaten = grainEatenByRats(bushels);
        bushels -= ratsEaten;
        //update land value
        price = newCostOfLand();
        printSummary();
    }
}

    finalSummary();

//input for buying acres
    int askHowManyAcresToBuy(int price, int bushels) {


        int acres = getNumber("Hammurabi how many acres you want to buy?");
        while (acres * price > bushels || acres < 0) {
            System.out.println("We have only" + bushels + "bushells left!");
        }
        return acres;
    }

    //input for selling acres
    int askHowManyAcresToSell(int acresOwned) {
        int acres = getNumber("how many acres you want to sell?");
        while (acres > acresOwned || acres < 0) {
            if (acres < 0) {
                System.out.println("are you okay or just making fun of me");

            } else {
                System.out.println("you can't sell more acres than you have boo!");
            }
            acres = getNumber("how many acres you esnt to sell?");
        }
        return acres;
    }

    //input for feeding people
    int askHowMuchGrainToFeedPeople(int bushels) {
        int grain = getNumber("how much grain you want to feed your people?");
        while (grain > bushels || grain < 0) {
            if (grain < 0) {
                System.out.println("you can't feed your people. leave the kingdom!");

            } else {
                System.out.println("We have only" + bushels + "bushels left!");
            }
            grain = getNumber("how much grain you want to feed your people?");
        }
        return grain;
    }

    //input for acres to plant with grain
    int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int acres = getNumber("how many acres you want to plan with grains?");
        while (acres > acresOwned || acres * population > bushels * 2 || acres < 0) {
            if (acres < 0) {
                System.out.println("you can plant -ve acres!!");
            } else if (acres > acresOwned) {
                System.out.println("you don't have that many acres");
            } else {
                System.out.println("we don't have that many people or grains to plant ");
            }
            acres = getNumber("how many acres you want to plan with grains");
        }
        return acres;
    }

    //plague deaths
    int plagueDeaths(int population) {
        if (rand.nextInt(100) < 15) {
            return population / 2;
        }
        return 0;
    }

    //starvation deaths
    int starvationDeaths(int population, int bushelsFedToPeople) {
        int bushelsIsNeeded = 20 * population;
        if (bushelsIsNeeded < bushelsFedToPeople) {
            return (bushelsFedToPeople - bushelsIsNeeded) / 20;
        }
        return 0;
    }

    //uprising
    boolean uprising(int population, int howManyPeopleStarved) {
        if (population == 0) return false;
        return (double) howManyPeopleStarved / population > 0.45;
    }

    //immigration
    int immigrants(int population, int acresOwned, int grainInStorage) {
        return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
    }

    //harvest
    int harvest(int acres, int bushelsUsedAsSeed) {
        return acres * (rand.nextInt(6) + 1);
    }

    // rats eating grain
    int grainEatenByRats(int bushels) {
        if (rand.nextInt(100) < 40) {
            return (int) (bushels * (rand.nextInt(21) + 10) / 100.0);

        }
        return 0;
    }

    //new land value

    int newCostofLand() {
        return rand.nextInt(7) + 17;
    }

}
//game display
void printSummary() {
    System.out.println("\n0 You are in year" + year + "of your ten year rule.");
    System.out.println("In the previous year"+ starvedPeople +"people starved to death");
    System.out.println("In the previous year" + immigrants + "people entered kingdom");
    System.out.println("The population is now" + population + ".");
    System.out.println("We harvested" + (bushels - 2800 + (year == 1 ? 0: grainEatenByRats(bushels) + askHowManyAcresToBuy(acresOwned, population, bushels)))
            + "bushels at" + (year == 1 ? 0 : (buschels - 2800 + grainEatenByRats(buschels) + askHowManyAcresToPlant(acresOwned, population, bushels)) /
            askHowManyAcresToPlant(acresOwned, population, bushels)) + "bushels per acre.");
    System.out.println("Rats destroyed" + (year == 1 ? 0 : grainEatenByRats(bushels)) + "bushels, leaving" + bushels + "bushels in storage.");
    System.out.println("The city owns" + acresOwned + "acres of land.");
    System.out.println("Land is currently worth " + landValue + "bushels per acre. \n");

}
void finalSummary() {
    System.out.println("Game Over!");
}





    /**
     * Prints the given message (which should ask the user for some integral
     * quantity), and returns the number entered by the user. If the user's
     * response isn't an integer, the question is repeated until the user
     * does give a integer response.
     *
     * @param message The request to present to the user.
     * @return The user's numeric response.
     */
    int getNumber (String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.nextInt() + "\" isn't a number!");
            }
        }
    }

private Random scanner;

public void main() {
}








