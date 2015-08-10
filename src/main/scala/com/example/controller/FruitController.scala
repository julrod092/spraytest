package com.example.controller

import com.example.domain.Fruit
import com.example.repository.FruitDAO
import spray.http.HttpResponse

import scala.util.parsing.json.JSONArray

class FruitController {
  private val fruitDAO = new FruitDAO

  def addFruit(fruit: Fruit): HttpResponse = {
    val create = fruitDAO.addFruit(fruit)
    create
  }

  def getAllFruits: String = {
    val getAllFruits = JSONArray((fruitDAO.getAllFruits).toList)
    getAllFruits.toString()
  }

  def sellFruit(fruit: Fruit) = {
    val sold = fruitDAO.sellFruit(fruit)
    sold
  }
}
