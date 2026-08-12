package org.lesson14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLUtilsTest {

    @Test
    void shouldCreateEmptyXmlElement() {
        // Arrange - подготовка
        String tagName = "user";
        String expectedResult = "<user></user>";
        // Act - действие
        String actualResult = XMLUtils.createEmptyElement(tagName);
        // Assert - сравнение
        assertEquals(expectedResult, actualResult, "Для корректного имени тега должен быть создан открывающий и закрывающий XML-тег"
        );
    }

    @Test
    void shouldReturnInvalidElementWhenTagNameIsNull() {
        String tagName = null;
        String expectedResult = "<invalid/>";
        String actualResult = XMLUtils.createEmptyElement(tagName);
        assertEquals(expectedResult, actualResult, "При передаче null метод должен вернуть <invalid/>"
        );
    }

    @Test
    void shouldReturnInvalidElementWhenTagNameIsEmpty() {
        String tagName = "";
        String expectedResult = "<invalid/>";
        String actualResult = XMLUtils.createEmptyElement(tagName);
        assertEquals(expectedResult, actualResult, "При передаче пустой строки метод должен вернуть <invalid/>"
        );
    }
}