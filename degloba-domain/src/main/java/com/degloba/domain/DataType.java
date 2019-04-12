package com.degloba.domain;

import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * Enum data type.
 */
public enum DataType {

    STRING {
                @Override
                public String getValue(String value) {
                    return StringUtils.isBlank(value) ? "" : value;
                }
            },
    INT {
                @Override
                public Integer getValue(String value) {
                    return StringUtils.isBlank(value) ? 0 : Integer.valueOf(value);
                }
            },
    LONG {
                @Override
                public Long getValue(String value) {
                    return StringUtils.isBlank(value) ? 0 : Long.valueOf(value);
                }
            },
    DOUBLE {
                @Override
                public Double getValue(String value) {
                    return StringUtils.isBlank(value) ? 0 : Double.valueOf(value);
                }
            },
    BIG_DECIMAL {
                @Override
                public BigDecimal getValue(String value) {
                    return StringUtils.isBlank(value) ? BigDecimal.ZERO : new BigDecimal(value);
                }
            },
    BOOLEAN {
                @Override
                public Boolean getValue(String value) {
                    return StringUtils.isBlank(value) ? false : Boolean.valueOf(value);
                }
            },
    DATE {
                @Override
                public Date getValue(String value) {
                    try {
                        return StringUtils.isBlank(value) ? null : DateUtils.parseDate(value, DATE_FORMAT);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("'" + value + "' cannot be converted to Date" + e);
                    }
                }
            },
    TIME {
                @Override
                public Date getValue(String value) {
                    try {
                        return StringUtils.isBlank(value) ? null : DateUtils.parseDate(value, TIME_FORMAT);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("'" + value + "' cannot be converted to Time" + e);
                    }
                }
            },
    DATE_TIME {
                @Override
                public Date getValue(String value) {
                    try {
                        return StringUtils.isBlank(value) ? null : DateUtils.parseDate(value, DATE_TIME_FORMAT);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("'" + value + "' cannot be converted to DateTime" + e);
                    }
                }
            };

    /**
     * Convert a string value of the corresponding data type in the form of value.
     * @param value Value of the string format
     * @return Object value
     */
    public abstract Object getValue(String value);

    /**
     * Date Format
     */
    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Time Format
     */
    protected static final String TIME_FORMAT = "hh:mm:ss";

    /**
     * Timestamp format
     */
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
}
