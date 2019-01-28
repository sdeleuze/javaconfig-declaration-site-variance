/**
 * Created by smallufo on 2019-01-25.
 */
package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import javax.inject.Inject

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [Config::class])
class TypeProjectionTest {

  @Inject
  private lateinit var ctx: Container<Tuple<String, String>>

  @Test
  fun testContext() {
  }

}

// Switch these 2 variants to see the difference of behavior when using declaration-site variance
// See https://kotlinlang.org/docs/reference/generics.html#declaration-site-variance for documentation
// The original issue was demonstrated via kotlin.Pair which is using declaration-site variance

// Variant 1: works when not using declaration-site variance
//class Tuple<A, B>

// Variant 2: Fails with using declaration-site variance
class Tuple<out A, out B>


private interface Container<T>
private class ContainerTuple : Container<Tuple<String, String>>

@Configuration
private open class Config {

  @Bean
  open fun containerPair() : ContainerTuple {
    return ContainerTuple()
  }

}
