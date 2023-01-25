import java.util.ArrayList;

/**
 * This class describes the ProcessedPrintJob attributes and methods. It is a
 * child class of PrintJob and inherits its attributes and methods.
 */
public class ProcessedPrintJob extends PrintJob{
    /**
     * The variables below store the prices of extra raw materials,
     * post-processing costs and the discount. They are declared as final to
     * prevent modification.
     */
    public static final double rawPriceAcrylic = 0.09;
    public static final double uvCuring = 0.01;
    public static final double uvProtectiveCoating = 0.005;
    public static final double polishing = 0.50;
    public static final double discount = 0.10;
    /**
     * This private variable stores the ArrayList of post-processing choices
     * as entered by the user. ArrayList is used as a different number of
     * post-processing choices are present for every processed print job.
     */
    private ArrayList<String> postProcessingChoices;

    /**
     * This is the class constructor for the ProcessedPrintJob class.
     * @param customerID The ID of the customer entered by the user.
     * @param printQuantity The quantity of items to be printed.
     * @param volume The volume of each item in cubic millimeters.
     * @param description The description of each print job.
     * @param plasticType The type of plastic used.
     * @param postProcessingChoices The ArrayList containing post-processing
     *                              choices.
     */
    public ProcessedPrintJob(String customerID,
                             int printQuantity,
                             double volume,
                             String description,
                             String plasticType,
                             ArrayList<String> postProcessingChoices) {
        super(customerID, printQuantity, volume, description,
                plasticType);
        this.postProcessingChoices = postProcessingChoices;
    }

    /**
     * This method formats the details of a processed print job to be
     * displayed in a tidy manner to the user. This method overrides the
     * displayAllDetails method that exists in the parent class.
     */
    @Override
    public void displayAllDetails() {
        System.out.println("\nDetails for Print Job ID: " + this.getPrintID());
        System.out.println("================================");
        System.out.printf("%-25s%s\n", "CustomerID: ", this.getCustomerID());
        System.out.printf("%-25s%d\n", "Print Quantity: ",
                this.getPrintQuantity());
        System.out.printf("%-25s%.2f\n", "Volume(mm3): ",
                this.getVolume());
        System.out.printf("%-25s%s\n", "Description: ", this.getDescription());
        System.out.printf("%-25s%s\n", "Type of Plastic: ",
                this.getPlasticType());
        System.out.printf("%-25s%s\n", "Post-Processing: ",
                this.getPostProcessingString());
        System.out.printf("%-25s%.2f\n", "Gross Price($): ", grossPrice());
        System.out.printf("%-25s%.2f\n", "Total Price($): ", calcTotalPrice());
        System.out.println("=================================\n");
    }

    /**
     * This method iterates the ArrayList of post-processing choices to store
     * in a string to be used in the display method.
     * @return String of post-processing choices.
     */
    private String getPostProcessingString() {
        String postProcessing = "";
        int i = 0;
        /*
         * While loop iterates the ArrayList to get the variables to store in
         * a string.
         */
        while (i < this.postProcessingChoices.size()) {
            postProcessing += "(" + (i+1) + ")";
            postProcessing += this.postProcessingChoices.get(i) + " ";
            i++;
        }
        return postProcessing;
    }

    /**
     * This method returns the gross price of a print job before base price
     * and discount considerations.
     * @return The gross price calculated before considerations.
     */
    @Override
    public double grossPrice() {
        String plasticType = this.getPlasticType();
        double grossPrice;
        double rawPrice = 0;
        double volume = this.getVolume();
        double totalPostProcessing = this.getPostProcessingPrice();

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
        } else if (plasticType.equals("Acrylic")) {
            rawPrice = rawPriceAcrylic;
        }
        /*
         *This formula below calculates the gross price of a processed print
         *job
         */
        grossPrice = ((rawPrice * volume) + totalPostProcessing)
                * this.getPrintQuantity();
        return grossPrice;
    }

    /**
     * This method returns the total cost of post-processing chosen by the user.
     * @return The total cost of post-processing per item.
     */
    private double getPostProcessingPrice() {
        double totalPostProcessing = 0;
        double volume = this.getVolume();
        int i = 0;
        /*
         * The while loop iterates the ArrayList to get the post-processing
         * choices then adds the cost to the double variable
         * totalPostProcessing.
         */
        while (i < this.postProcessingChoices.size()) {
            /*
             * If-else if statements to add the costs of post-processing to
             * the total.
             */
            if (this.postProcessingChoices.get(i).equals("UV Curing")) {
                totalPostProcessing += (uvCuring * volume);
            } else if (this.postProcessingChoices.get(i).equals("UV " +
                    "Protective Coating")) {
                totalPostProcessing += (uvProtectiveCoating * volume);
            } else if (this.postProcessingChoices.get(i).equals("Polishing")) {
                totalPostProcessing += polishing;
            }
            i++;
        }
        return totalPostProcessing;
    }

    /**
     * This method returns the total price calculated if the gross price does
     * not meet the base price or if the quantity of a processed print job
     * exceeds 500 items to attract a discount.
     * @return The total price after considerations.
     */
    @Override
    public double calcTotalPrice() {
        double totalPrice;
        double grossPrice = this.grossPrice();
        double quantity = this.getPrintQuantity();
        /*
         * This if-else statement checks if the gross price has met the base
         * price requirements. If it does, the quantity of items will be
         * checked to see if a discount should be applied.
         */
        if (grossPrice < basePrice) {
            totalPrice = basePrice;
        } else {
            if (quantity > 500) {
                totalPrice = grossPrice - (grossPrice * discount);
                /*
                 * This ensures that if the discount brings the total price
                 * to below the base price that the base price requirements
                 * are met.
                 */
                if (totalPrice < basePrice) {
                    totalPrice = basePrice;
                }
            } else {
                totalPrice = grossPrice;
            }
        }
        return totalPrice;
    }
}
