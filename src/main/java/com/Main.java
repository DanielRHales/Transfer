package com;

import com.util.Transfer;

import java.awt.*;
import java.io.File;

/**
 * @author Daniel
 */
public class Main {

    private final File outdated;
    private final File updated;

    public static void main(String... argv) {
        new Main(argv);
    }

    private Main(String... argv) {
        if (argv.length != 2) {
            throw new RuntimeException("Incorrect parameter arguments.");
        } else {
            outdated = new File(argv[0]);
            updated = new File(argv[1]);
            if (!outdated.exists()) {
                throw new RuntimeException(String.format("File '%s' does not exist.", outdated.getAbsolutePath()));
            }
            if (outdated.isDirectory()) {
                throw new RuntimeException(String.format("File '%s' is a directory.", outdated.getAbsolutePath()));
            }
            if (!updated.exists()) {
                throw new RuntimeException(String.format("File '%s' does not exist.", updated.getAbsolutePath()));
            }
            if (updated.isDirectory()) {
                throw new RuntimeException(String.format("File '%s' is a directory.", updated.getAbsolutePath()));
            }
            initialize();
        }
    }

    private void initialize() {
        EventQueue.invokeLater(new Transfer(outdated, updated));
    }

}
