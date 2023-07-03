package com.example.product.Ulti;

import com.example.product.model.Bill;
import com.example.product.service.bill.request.BillSaveRequest;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppUtils {
    public static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String s) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(s, formatter);
            }
        };
        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(toStringDate);
    }

}
