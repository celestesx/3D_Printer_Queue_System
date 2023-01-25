/**
 * This class describes the PrintJob attributes and methods. It is the parent
 * class of ProcessedPrintJob.
 */
public class PrintJob {
    /**
     * The variables below store the state of the PrintJob object and are
     * private to restrict visibility by other classes.
     */
    private int printID;
    private String customerID;
    private String description;
    private int printQuantity;
    private double volume;
    private String plasticType;
    /**
     * The count variable is static as it is independent of the objects and
     * is needed to keep track of the increasing job IDs with each print job
     * creation.
     */
    protected static int count = 100;
    /**
     * These static variables below store the prices of the raw materials and
     * base price.They are declared as final so that the value cannot be
     * modified.
     */
    public static final double rawPricePLA = 0.05;
    public static final double rawPriceABS = 0.06;
    public static final double rawPriceNylon = 0.07;
    public static final double basePrice = 60.50;

    /**
     * The following are accessor methods to return a value of the private
     * variables declared at the top.
     * @return The value of the private variables.
     */
    public int getPrintID() {
        return printID;
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getDescription() {
        return description;
    }
    public int getPrintQuantity() {
        return printQuantity;
    }
    public double getVolume() {
        return volume;
    }
    public String getPlasticType() {
        return plasticType;
    }

    /**
     * This is the class constructor of the PrintJob class.
     * @param customerID The ID of the customer entered by the user.
     * @param printQuantity The quantity of items to be printed.
     * @param volume The volume of each item in cubic millimeters.
     * @param description The description of a print job.
     * @param plasticType The type of plastic used.
     */
    public PrintJob(String customerID,
                    int printQuantity,
                    double volume,
                    String description,
                    String plasticType) {
        this.printID = count++;
        this.customerID = customerID;
        this.printQuantity = printQuantity;
        this.volume = volume;
        this.description = description;
        this.plasticType = plasticType;
    }

    /**
     * This method formats the details of a print job to be displayed in a
     * tidy manner to the user.
     */
    public void displayAllDetails() {
        System.out.println("\nDetails for Print Job ID: " + this.getPrintID());
        System.out.println("================================");
        System.out.printf("%-25s%s\n", "CustomerID: ", this.getCustomerID());
        System.out.printf("%-25s%d\n", "Print Quantity: ",
                this.getPrintQuantity());
        System.out.printf("%-25s%.2f\n", "Volume(mm3): ", this.getVolume());
        System.out.printf("%-25s%s\n", "Description: ", this.getDescription());
        System.out.printf("%-25s%s\n", "Type of Plastic: ",
                this.getPlasticType());
        System.out.printf("%-25s%.2f\n", "Gross Price($): ", this.grossPrice());
        System.out.printf("%-25s%.2f\n", "Total Price($): ",
                this.calcTotalPrice());
        System.out.println("=================================\n");
    }

    /**
     * This method returns the gross price of a print job before base price
     * considerations.
     * @return The gross price calculated before base price consideration.
     */
    public double grossPrice() {
        String plasticType = this.getPlasticType();
        double grossPrice;
        double rawPrice = 0;
        double volume = this.getVolume();
        /*
         * The if-else if statement assigns the price of a raw material by
         * comparing the String in the plasticType variable.
         */
        if (plasticType.equals("PLA")) {
            rawPrice = rawPricePLA;
        } else if (plasticType.equals("ABS")) {
            rawPrice = rawPriceABS;
        } else if (plasticType.equals("Nylon")) {
            rawPrice = rawPriceNylon;
        }
        /* This formula below calculates the gross price of a print job */
        grossPrice = (rawPrice * volume) * this.getPrintQuantity();
        return grossPrice;
    }

    /**
     * This method returns the total price calculated if the gross price does
     * not meet the base price.
     * @return The total price after base price consideration.
     */
    public double calcTotalPrice() {
        double totalPrice;
        double grossPrice = this.grossPrice();
        /*
         * This if - else statement checks if the gross price has met the
         * base price requirements.
         */
        if (grossPrice < basePrice) {
            totalPrice = basePrice;
        } else {
            totalPrice = grossPrice;
        }
        return totalPrice;
    }
}
