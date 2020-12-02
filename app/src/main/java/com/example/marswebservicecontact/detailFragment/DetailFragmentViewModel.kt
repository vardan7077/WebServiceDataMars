package com.example.marswebservicecontact.detailFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.marswebservicecontact.R
import com.example.marswebservicecontact.network.MarsClass


class DetailFragmentViewModel(marsProperty:MarsClass, application: Application):AndroidViewModel(application) {

    private val _selectedProperty = MutableLiveData<MarsClass>()
    val selectedProperty: LiveData<MarsClass>
        get() = _selectedProperty

    val displayPropertyPrice = Transformations.map(selectedProperty) {
         application.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        application.applicationContext.getString(R.string.display_type,
            application.applicationContext.getString(
                when(it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }

    init{
        _selectedProperty.value = marsProperty
    }
}