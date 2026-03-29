package com.example.expensebanktracker.util;

import com.example.expensebanktracker.entity.Transaction;
import com.opencsv.CSVReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    public static List<Transaction> parse(MultipartFile file) throws Exception {
        List<Transaction> list = new ArrayList<>();

        CSVReader reader = new CSVReader(
                new InputStreamReader(file.getInputStream())
        );

        String[] line;
        boolean firstLine = true;

        while ((line = reader.readNext()) != null) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            Transaction t = new Transaction();
            t.setAmount(Double.parseDouble(line[0]));
            t.setDescription(line[1]);
            t.setDate(LocalDate.parse(line[2]));

            list.add(t);
        }

        return list;
    }
}