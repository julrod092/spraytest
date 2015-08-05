package com.example.controller

import com.example.domain.Fruit
import com.example.repository.FruitDAO

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
