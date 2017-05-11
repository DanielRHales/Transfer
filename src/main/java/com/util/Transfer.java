package com.util;

import com.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Transfer implements Runnable {

    private final File outdated;
    private final File updated;

    public Transfer(File outdated, File updated) {
        this.outdated = outdated;
        this.updated = updated;
    }

    @Override
    public void run() {
        final File transfer = new File(outdated.getParentFile().getAbsolutePath(), outdated.getName());
        if (!outdated.delete()) {
            throw new RuntimeException(String.format("Unable to remove outdated program file '%s'.", outdated.getAbsolutePath()));
        }
        if (!updated.renameTo(transfer)) {
            throw new RuntimeException(String.format("Unable to rename updated program file '%s'.", updated.getAbsolutePath()));
        }
        execute(transfer);
    }

    private void execute(File file) {
        try {
            new ProcessBuilder("java", "-jar", file.getAbsolutePath()).start();
        } catch (IOException ex) {
            Logger.log(Transfer.class, Level.SEVERE, String.format("Unable to execute program file '%s'.", updated.getAbsolutePath()), ex);
        }
    }
}
