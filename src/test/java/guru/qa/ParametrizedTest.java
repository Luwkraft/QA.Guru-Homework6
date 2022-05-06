package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;




public class ParametrizedTest {

        @ValueSource(strings = {"Time", "Space"})
        @ParameterizedTest(name = "Проверка поиска в Википедии по слову {0}, результат должен содержать \"{1}\"")
         void SearchTest(String testData){
                Selenide.open("https://en.wikipedia.org/wiki/");
                $("#searchInput").setValue(testData);
                $("#searchButton").click();
                $("#content").shouldHave(text(testData));

        }


        @CsvSource(value = {"Time | Time is the continued sequence of existence and events",
                "Space | Space is the boundless three-dimensional extent"},
                delimiter = '|')
        @ParameterizedTest(name = "Проверка поиска в Википедии по слову {0}, результат должен содержать \"{1}\"")
          void SearchComplexTest(String testData, String  expectedResult) {
                Selenide.open("https://en.wikipedia.org/wiki/");
                $("#searchInput").setValue(testData);
                $("#searchButton").click();
                $("#content").shouldHave(text(expectedResult));
        }

        static Stream<Arguments> methodSourceExampleTest() {
            return Stream.of(
                    Arguments.of("Time", "Time is the continued sequence of existence and events"),
                    Arguments.of("Space", "Space is the boundless three-dimensional extent")
            );
        }
        @MethodSource("methodSourceExampleTest")
        @ParameterizedTest
        void methodSourceExampleTest(String first, String second) {
            Selenide.open("https://en.wikipedia.org/wiki/");
            $("#searchInput").setValue(first);
            $("#searchButton").click();
            $("#content").shouldHave(text(second));

        }
}
