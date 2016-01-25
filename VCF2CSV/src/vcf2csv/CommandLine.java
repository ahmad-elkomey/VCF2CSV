/*
 * 
 */
package vcf2csv;

/**
 * This class is the command line handler. It's used to fetch meaningful data
 * from the passed arguments.
 *
 * @author Ahmad
 */
public class CommandLine {

    public static final int MAX_ALLOWED_ARGUMENTS = 4;
    public static final int MIN_ALLOWED_ARGUMENTS = 2;
    private final String VCF_FILE_PATH;
    private final String CSV_FILE_PATH;
    private final boolean DO_REVERSE;
    private final boolean IS_SET;

    public CommandLine(String[] arguments) {

        /**
         * checking the arguments. if less than 2 (meaning it's either empty or
         * containing only 1 argument), the first argument isn't convert or more
         * than 4 (meaning there is more than expected input), then do not
         * continue initializing valid data.
         */
        if (arguments.length < MIN_ALLOWED_ARGUMENTS
                || arguments.length > MAX_ALLOWED_ARGUMENTS
                || !arguments[0].equals("convert")) {
            VCF_FILE_PATH = "";
            CSV_FILE_PATH = "";
            DO_REVERSE = false;
            IS_SET = false;
            return;
        }

        /**
         * reaching this statement means there are at least two arguments and
         * the first is convert.
         */
        IS_SET = true; // stating that the state is valid.
        VCF_FILE_PATH = parseSrc(arguments[1]); // fetching the source file path.

        /**
         * trying to interpret the destination path and the reverse option.
         */
        // no destination, no reverse
        if (arguments.length == 2) {
            DO_REVERSE = false;
            CSV_FILE_PATH = setupDst();
            return;
        }

        if (arguments[2].equals("-r")) {
            // no destination, reverse
            DO_REVERSE = true;
            CSV_FILE_PATH = setupDst();
        } else {
            // there is a destination supplied
            if (arguments[2].endsWith("/") || arguments[2].endsWith("\\")) { // this is a directory
                CSV_FILE_PATH = setupDstByDirectory(arguments[2]);
            } else { // this is a file name
                CSV_FILE_PATH = setupDstByFileName(arguments[2]);
            }

            DO_REVERSE = arguments.length == 4 && arguments[3].equals("-r");
        }

    }

    public String getVCFFilePath() {
        return VCF_FILE_PATH;
    }

    public String getCSVFilePath() {
        return CSV_FILE_PATH;
    }

    public boolean doReverse() {
        return DO_REVERSE;
    }

    public boolean isSet() {
        return IS_SET;
    }

    /**
     * parses the given source path and outputs the ready to use source
     * path.This method merely adds the vcf file extension if it doesn't exist.
     *
     * @param srcPath given source path string
     * @return returns string that is ready to use as a source file path.
     */
    private String parseSrc(String srcPath) {
        if (srcPath.endsWith(".vcf")) {
            return srcPath;
        }
        return srcPath + ".vcf";
    }

    /**
     * returns a string that is ready to use to open/create a csv file. This
     * method relies mainly on the <code>VCF_FILE_PATH</code> string. This
     * method shouldn't be called except after setting the
     * <code>VCF_FILE_PATH</code>. This method merely uses the same vcf file
     * path with replacing the extension with "csv".
     *
     * @return returns a string that is ready to use to open/create a csv file.
     */
    private String setupDst() {
        return VCF_FILE_PATH.substring(0, VCF_FILE_PATH.length() - 3) + "csv";
    }

    /**
     * returns a string that is ready to use to open/create a csv file. This
     * method relies mainly on the <code>VCF_FILE_PATH</code> string. This
     * method shouldn't be called except after setting the
     * <code>VCF_FILE_PATH</code>. This method uses the vcf file name to be
     * added to the directory path passed.
     *
     * @param directory string holding the directory path that should contain
     * the csv file. The parameter should end with a slash.
     * @return returns a string that is ready to use to open/create a csv file.
     */
    private String setupDstByDirectory(String directory) {
        int fileNameIndex = VCF_FILE_PATH.lastIndexOf("/"); // that's on Linux
        if (fileNameIndex == -1) { // that's on Windows
            fileNameIndex = VCF_FILE_PATH.lastIndexOf("\\");
        }
        String output = directory + VCF_FILE_PATH.substring(fileNameIndex);
        return output.substring(0, output.length() - 3) + "csv";
    }

    /**
     * returns a string that is ready to use to open/create a csv file. This
     * method just adds the csf file extension to the passed parameter if it
     * doesn't exist.
     *
     * @param fileName string holding the path to the csv file to be
     * opened/created.
     * @return returns a string that is ready to use to open/create a csv file.
     */
    private String setupDstByFileName(String fileName) {
        if (fileName.endsWith(".csv")) {
            return fileName;
        }
        return fileName + ".csv";
    }
}
