package hammurabi;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public void main() {


    class Hammurabi {
        //random number generator
        //Scanner for input

        //game variables
        int year = 1;
        int starvedPeople = 0;
        int immigrants = 0;
        int population = 100;
        int bushels = 2800;
        int acresOwned = 1000;
        int price = 19; // bushels/acre
        private String Hammurabi;
        public String name = Hammurabi;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);




        //create new hamurabi game object
        public void main(String[] args) {
            hammurabi game = new hammurabi(); //start the game
            game.playGame();
        }

        void playGame() { //display initial game state
            printSummary();
            //game loop for 10years
            for (year = 1; year <= 10; year++) {
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
            finalSummary();
        }

        private int immigrants(int population, int acresOwned, int bushels) {
            return population;
        }

        private int harvest(int acresToPlant, int acresToPlant1) {
            return acresToPlant;
        }

        private int plagueDeaths(int population) {
            return population;
        }

        private int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
            return acresOwned;
        }

        private int askHowMuchGrainToFeedPeople(int bushels) {
            return bushels;
        }

        private int starvationDeaths(int population, int bushelsFedToPeople) {
            return population;
        }

        private boolean uprising(int population, int starvedPeople) {
            return false;
        }

        private int grainEatenByRats(int bushels) {
            return bushels;
        }

        private int newCostOfLand() {
            return 0;
        }

        private void printSummary() {
        }

        private void finalSummary() {
        }
//input for buying acres
int askHowManyAcresToBuy(int price, int bushels) {


    int acres = getNumber("Hammurabi how many acres you want to buy?");
    while (acres * price > bushels || acres < 0) {
        System .out.println("We have only" + bushels + "bushells left!");
    }
    return acres;
}
//input for selling acres
int askHowManyAcresToSell(int acresOwned) {
            int acres = getNumber("how many acres you want to sell?");
            while (acres > acresOwned || acres < 0) {
                if (acres < 0) {
            }
}
    }
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








