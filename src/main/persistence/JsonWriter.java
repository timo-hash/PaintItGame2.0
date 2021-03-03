package persistence;

import model.Leaderboard;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//code is heavily based onJsonSerializationDemo

// Represents a writer that writes JSON representation of leaderboard to file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    protected String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of leaderboard to file
    public void write(Leaderboard lb) {
        JSONObject json = lb.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }


    // EFFECTS: output true if error was encountered in a method call in writer, false otherwise
    public boolean errorState() {
        return writer.checkError();
    }

}
