/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcf2csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;

/**
 * The vcf to csv file converter. This class holds the logic and uses all other
 * classes included to perform the conversion from the vcf file to csv file for
 * Arabic data only.
 *
 * @author Ahmad
 */
public class VCF2CSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * start by parsing the input data
         */
        CommandLine commandLine = new CommandLine(args);

        /**
         * If it's not set, don't continue and display the help message.
         */
        if (!commandLine.isSet()) {
            System.out.println(getHelpMessage());
            return;
        }

        /**
         * Seems like the input is valid. Let's start by inspecting the input a
         * little bit more. Namely, we'll first try opening the vcf and csv
         * files.
         */
        System.out.println("Seems like valid data input");
        String vcfFilePath = commandLine.getVCFFilePath();
        String csvFilePath = commandLine.getCSVFilePath();

        // try opening vcf file.
        BufferedReader vcfFile = null;
        try {
            vcfFile = new BufferedReader(new FileReader(vcfFilePath));
        } catch (IOException ex) {
            System.out.println("Something went wrong while trying to open the "
                    + "vcf file!\nMake sure the path is correct and the file is "
                    + "accessible.");
            return;
        }

        // try opening the csv file.
        BufferedWriter csvFile = null;
        try {
            csvFile = new BufferedWriter(new FileWriter(csvFilePath));
        } catch (IOException ex) {
            System.out.println("Something went wrong while trying to open the "
                    + "csv file.");
            return;
        }
        System.out.println("Valid Data Input.");

        /**
         * Start parsing the file.
         */
        String line = null;
        Contact contact = null;
        QuotedPrintableCodec decoder = new QuotedPrintableCodec();
        while (true) {

            /**
             * read a line. Exit in case of error.
             */
            try {
                line = vcfFile.readLine();
                if (line == null) {
                    vcfFile.close();
                    csvFile.close();
                    return; // nothing more to read.
                }
            } catch (IOException ex) {
                System.out.println("Something went wrong while trying to read from the vcf file!");
                return;
            }

            /**
             * building the record/contact
             */
            if (line.startsWith("N;")) {
                contact = new Contact(extractName(line));
            } else if (line.startsWith("=")) {
                contact.setName(contact.getName() + extractName(line.substring(1)));
            } else if (line.startsWith("TEL")) {
                contact.addPhone(extractPhone(line));
            } else if (line.startsWith("END")) {
                // now, that's the end of a record
                try {
                    if (commandLine.doReverse()) {
                        csvFile.write(decoder.decode(contact.getFullNameReversed()) + ", ");
                    } else {
                        csvFile.write(decoder.decode(contact.getFullName()) + ", ");
                    }
                    csvFile.write(String.join(",", contact.getPhones()));
                    csvFile.newLine();
                } catch (IOException ex) {
                    System.out.println("Something went wrong while trying to "
                            + "write a record to the csv file!");
                    return;
                } catch (DecoderException ex) {
                    System.out.println("Someting went wrong while trying to decode a record!");
                }
            }
        }
    }

    public static String getHelpMessage() {
        return "format:\n"
                + "convert srcPath [dstPath] [-r]\n"
                + "srcPath is that path to the vcf file. The extension is optional.\n"
                + "dstPath is the path to the csv file. This is an optional "
                + "parameter. The value of this parameter can be either a "
                + "directory in which you want the output csv file to be "
                + "located (in this case, you should end it with a slash), and "
                + "the output file will hold the same source file name. Or you "
                + "can enter a full path to a csv file. The extension is "
                + "optional too.\n"
                + "-r optional paramter. This causes the output file to hold the"
                + " names read from the vcf file but reversed.";
    }

    public static String extractName(String string) {
        String[] splitted = string.split(":");
        return splitted[splitted.length - 1];
    }

    public static String extractPhone(String string) {
        String[] splitted = string.split(":");
        return splitted[splitted.length - 1];
    }

}
