package com.example.controller

import com.example.domain.Fruit
import com.example.repository.FruitDAO

import scala.util.parsing.json.JSONArray

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

  def getAllFruits: String = {
    val getAllFruits = JSONArray((fruitDAO.getAllFruits).toList)
    getAllFruits.toString()
  }
}
