package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    String[]current;


    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    CSVReader(String filename,String delimiter) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = false;
    }

    CSVReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = ";";
        this.hasHeader = false;
    }

    CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.columnLabelsToInt = new HashMap<>();
        this.reader = new BufferedReader(reader);
        this.delimiter =delimiter;
        this.hasHeader = hasHeader;

        if (hasHeader) {
            parseHeader();
        }
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabelsToInt.put(header[i],i);
            columnLabels.add(header[i]);
        }
    }

    boolean next() {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            return false;
        }
        if (line == null)
            return false;
        current = line.split(delimiter);
        return true;
    }

    List<String> getColumnLabels(){ //zwraca etykiety kolumn
        return this.columnLabels;
    }

    int getRecordLength() { //zwraca długość bieżącego wczytanego rekordu
        return current.length;
    }

    boolean isMissing(int columnIndex) {//czy wartość istnieje w bieżącym rekordzie
        return columnIndex < current.length && columnIndex >= 0 && !Objects.equals(current[columnIndex], "");
    }

    boolean isMissing(String columnLabel){
        return !Objects.equals(current[columnLabelsToInt.get(columnLabel)], "")
                && columnLabelsToInt.get(columnLabel) >= 0
                && columnLabelsToInt.get(columnLabel) < current.length;
    }

    String get(int columnIndex){
        try {
            return current[columnIndex];
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("bad index");
        }
    }

    String get(String columnLabel){
        try {
            return current[columnLabelsToInt.get(columnLabel)];
        }catch (IndexOutOfBoundsException e)
        {
            throw new IndexOutOfBoundsException("bad index");
        }
    }

    int getInt(int columnIndex){
        return Integer.parseInt(get(columnIndex));
    }

    int getInt(String columnLabel){
        return Integer.parseInt(get(columnLabel));
    }

    long getLong(int columnIndex){
        return Long.parseLong(get(columnIndex));
    }

    long getLong(String columnLabel){
        return Long.parseLong(get(columnLabel));
    }

    double getDouble(int columnIndex){
        return Double.parseDouble(get(columnIndex));
    }

    double getDouble(String columnLabel){
        return Double.parseDouble(get(columnLabel));
    }

    LocalTime getTime(int columnIndex, String format){
        return LocalTime.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }

    LocalDate getDate(int columnIndex, String format) {
        return LocalDate.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }

}
