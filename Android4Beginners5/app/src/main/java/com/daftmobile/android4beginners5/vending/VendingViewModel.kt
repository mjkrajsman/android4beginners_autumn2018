package com.daftmobile.android4beginners5.vending

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.daftmobile.android4beginners5.SingleLiveEvent
import java.lang.Exception

class VendingViewModel: ViewModel() {
    private val vendingMachine = ChocoBarVendingMachine()
    private val chocoBarLiveData = SingleLiveEvent<String>()
    private val depositLiveData = SingleLiveEvent<String>()
    private val errorLiveData = MutableLiveData<String>()

    fun deposit(): LiveData<String> = depositLiveData
    fun chocoBarVended(): LiveData<String> = chocoBarLiveData
    fun vendingError(): LiveData<String> = errorLiveData

    init {
        refreshDeposit()
    }

    fun depositCoin() {
        vendingMachine.depositCoin()
        refreshDeposit()
    }

    fun vend(barName: String) {
        try {
            val bar = vendingMachine.vend(barName)
            chocoBarLiveData.value = "You vended ${bar.name}"
            refreshDeposit()
        }catch(e: ItemNotFoundException){
            errorLiveData.value = "${e.itemName} not found!"
        }catch(e: OutOfStockException){
            errorLiveData.value = "No ${e.barName} left!"
        }catch(e: InsufficientFundsException){
            errorLiveData.value = "Insufficient funds!"
        }catch(e: Exception){
            errorLiveData.value = e.message
        }finally{
            //optional
        }
    }

    private fun refreshDeposit() {
        depositLiveData.value = "Coins: ${vendingMachine.getCurrentDeposit()}"
    }
}
