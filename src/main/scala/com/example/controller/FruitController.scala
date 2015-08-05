package com.example.controller

import com.example.domain.{Fruit, User}
import com.example.repository.FruitDAO

/**
 * Created by sebastian.garces on 05/08/2015.
 */
class FruitController {
  private val fruitDAO = new FruitDAO

  def addFruit(fruit: Fruit): Boolean = {
    val create = fruitDAO.addFruit(fruit)
    if (create) {
      true
    } else {
      false
    }
  }
}
