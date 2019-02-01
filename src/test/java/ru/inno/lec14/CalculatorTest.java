package ru.inno.lec14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    private Calculator calc;

    @BeforeAll
    static void init(){
        LOGGER.info("init");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        LOGGER.info("setup");
    }

    @Test
    void sum() {
        LOGGER.info("summ");
        assertEquals(7, calc.sum(3,4), "3 + 4 = 7");
        assertEquals(-1, calc.sum(3,-4), "3 -4 = -1");
    }

    @Test
    void divide() throws DivideByZeroException {
        LOGGER.info("divide");

        assertEquals(2,calc.divide(4,2),"4 / 2 = 2");
        assertEquals(1,calc.divide(3,2),"3 / 2 = 1");
        assertThrows(DivideByZeroException.class, () -> calc.divide(3,0),"3 / 0 = ?");
    }
}