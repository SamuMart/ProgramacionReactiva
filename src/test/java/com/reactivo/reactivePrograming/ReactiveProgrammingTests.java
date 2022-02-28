package com.reactivo.reactivePrograming;

import com.reactivo.reactiveprograming.ReactiveProgramming;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ReactiveProgrammingTests {

    @Test
    void testMapFromStringToBigDecimal(){
        assertEquals(0, BigDecimal.TEN.compareTo(new ReactiveProgramming().mapFromStringToBigDecimal("10")));
    }

    @Test
    void testMonoNoEmptyWithMonoEmpty(){
        StepVerifier.create(new ReactiveProgramming().monoNoEmpty(Mono.empty()))
                .expectNext(0)
                .expectComplete()
                .verify();

    }

    @Test
    void testMonoNoEmptyWithMono(){
        StepVerifier.create(new ReactiveProgramming().monoNoEmpty(Mono.just(1)))
                .expectNext(1)
                .expectComplete()
                .verify();
    }

    @Test
    void testMapMonoFromStringToBigDecimalStepVerifier(){
        StepVerifier.create(new ReactiveProgramming().mapMonoFromStringToBigDecimal(Mono.just("10")))
                .expectNext(BigDecimal.TEN)
                .expectComplete()
                .verify();
    }

    @Test
    void testMapFluxFromStringToBigDecimalStepVerifier(){
        StepVerifier.create(new ReactiveProgramming().mapFluxFromStringToBigDecimal(
                        Flux.interval(Duration.ofMillis(10)).map(String::valueOf)).take(5))
                .expectNext(BigDecimal.ZERO,BigDecimal.ONE)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void testFilterPositive(){
        StepVerifier.create(new ReactiveProgramming().filterPositive(Flux.just(1,4,-7,8,13)))
                .expectNextCount(2)
                .expectError()
                .verify();
    }

    @Test
    void testMapMonoFromStringBigDecimalMas099(){
        StepVerifier.create(new ReactiveProgramming().mapMonoFromStringBigDecimalMas099(
                Mono.just("10"))).expectNext(new BigDecimal("10.99"))
                .expectComplete()
                .verify();
    }

    @Test
    void testFilterEven(){
        StepVerifier.create(new ReactiveProgramming().filterEven(
                Flux.just(1,4,7,8,13)))
                .expectNext(4,8)
                .expectComplete()
                .verify();

    }

    @Test
    void TestLogs(){
        StepVerifier.create(new ReactiveProgramming().logs(
                Flux.just(1,4,7,8,13)))
                .expectNext(1,4,7,8,13)
                .expectComplete()
                .verify();


    }
}
