/**
 * This class describes the PrintQueue attributes and methods. It is
 * implements the 'first-in-first-out' function of the program.
 */
public class PrintQueue {
    /**
     * This variable stores a 1-Dimensional array of PrintJob objects.
     */
    public PrintJob[] printQueue;

    /**
     * This is the class constructor for the PrintQueue class. It initialises
     * the printQueue array.
     * @param queueSize The size of the array as entered by the user.
     */
    public PrintQueue(int queueSize) {
        this.printQueue = new PrintJob[queueSize];
    }

    /**
     * This method adds PrintJob objects to the printQueue array.
     * @param printJob Object obtained from StageB as entered by the user.
     */
    public void addToQueue(PrintJob printJob) {
        int i = 0;
        /*
         * This variable stores the number of PrintJob objects entered into
         * the array.
         */
        int count = this.countJobs();
        /*
         * While statement to iterate the printQueue array to find the next
         * null space then assigns the PrintJob object to it.
         */
        while (i <= count) {
            if(this.printQueue[i] == null) {
                this.printQueue[i] = printJob;
            }
            i++;
        }
    }

    /**
     * This method iterates the printQueue array then calls the display
     * method from the object classes.
     */
    public void printDetails() {
        int i = 0;
        /*
         * The while statement iterates the printQueue array to display the
         * indices which are not null.
         */
        while (i < this.printQueue.length) {
            if (this.printQueue[i] != null) {
                this.printQueue[i].displayAllDetails();
            }
            i++;
        }
    }

    /**
     * This method takes an input of print ID from the user and compares it
     * to the print ID with every PrintJob object in the array.
     * @param searchInput Integer entered by the user to represent print ID.
     * @return Boolean to indicate an entry has been found.
     */
    public boolean searchPrintJob(int searchInput) {
        int i = 0;
        int count = 0;
        boolean found = false;
        /*
         * This while loop iterates the printQueue to compare the printID
         * attribute with the search input.
         */
        while (i < this.printQueue.length) {
            if (this.printQueue[i] != null) {
                if (searchInput == this.printQueue[i].getPrintID()) {
                    count = i;
                    found = true;
                }
            }
            i++;
        }
        /*
         * This if statement uses the count variable to access the index and
         * calls the PrintJob display method.
         */
        if (found == true) {
            this.printQueue[count].displayAllDetails();
        }
        return found;
    }

    /**
     * This method 'removes' an entry from the front of the queue. It does so
     * by reassigning the objects to different indices.
     */
    public void removeFromQueue() {
        int i = 0;
        int j = 1;
        int count = 0;
        /*
         * This while loop iterates the printQueue array to reassign the
         * indices to the one after it to simulate a 'first-in-first-out'
         * queue.
         */
        while (j < this.printQueue.length) {
            this.printQueue[i] = this.printQueue[j];
            if(this.printQueue[j] != null) {
                count++;
            }
            i++;
            j++;
        }
        /* Nullifying the index after the last recorded PrintJob */
        this.printQueue[count] = null;
    }

    /**
     * This method counts the PrintJob objects in the printQueue.
     * @return integer representing the number of objects in array.
     */
    public int countJobs() {
        int i = 0;
        int count = 0;
        /*
         * This while loop iterates the printQueue array to count the indices
         * that are not null.
         */
        while (i < this.printQueue.length) {
            if (this.printQueue[i] != null) {
                count++;
            }
            i++;
        }
        return count;
    }

    /**
     * This method checks if the array is full.
     * @return boolean value representing the array is full to return true.
     */
    public boolean checkIfQueueIsFull() {
        boolean full = true;
        int i = 0;
        /*
         * While loop iterates the printQueue array to find a null value. If
         * it exists to return a false value.
         */
        while (i < this.printQueue.length) {
            if (this.printQueue[i] == null) {
                full = false;
            }
            i++;
        }
        return full;
    }

    /**
     * This method checks if the array is empty.
     * @return boolean value representing the array is empty to return true.
     */
    public boolean checkIfQueueIsEmpty() {
        boolean empty = true;
        int i = 0;
        /*
         * While loop iterates the printQueue array to find any value that is
         * not null to return a false value.
         */
        while (i < this.printQueue.length) {
            if (this.printQueue[i] != null) {
                empty = false;
            }
            i++;
        }
        return empty;
    }
}
