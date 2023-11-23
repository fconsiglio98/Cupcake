package com.example.cupcake.adapters


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import androidx.recyclerview.widget.RecyclerView
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat
import com.example.cupcake.model.Flavor
import com.example.cupcake.viewModel.OrderViewModel


class FlavorAdapter(
    private val orderViewModel: OrderViewModel,
    private val context: Context
) : RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class FlavorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(com.example.cupcake.R.id.label)

        val quantityTextView: TextView = view.findViewById(com.example.cupcake.R.id.quantityTextView)
        val minusButton: Button = view.findViewById(com.example.cupcake.R.id.minusButton)
        val plusButton: Button = view.findViewById(com.example.cupcake.R.id.plusButton)
        val imageView: ImageView = view.findViewById(com.example.cupcake.R.id.flavorIcon)

    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(com.example.cupcake.R.layout.fragment_flavor_number, parent, false)


        return FlavorViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FlavorViewHolder, position: Int) {
        val item = orderViewModel.dataset[position]
        var text = item.name
        if (position == 1) {
            text += "*"
        }

        val vector = VectorChildFinder(context, com.example.cupcake.R.drawable.ic_vanilla_cupcake_24dp, holder.imageView)

        item.getColorMap().forEach { color ->
            vector.findPathByName(color.key).fillColor = color.value
        }
        holder.textView.text = text
        holder.quantityTextView.text = item.quantity.value.toString()
        holder.plusButton.setOnClickListener() {
            if (orderViewModel.remainingQuantity.value!! > 0) {
                orderViewModel.setRemainingQuantity(orderViewModel.remainingQuantity.value!! - 1)
                item.setQuantity(item.quantity.value!! +1)
                holder.quantityTextView.text = item.quantity.value.toString()
            }

        }
        holder.minusButton.setOnClickListener() {
            if (orderViewModel.remainingQuantity.value!! <= 6 && item.quantity.value!! > 0) {
                orderViewModel.setRemainingQuantity(orderViewModel.remainingQuantity.value!! + 1)
                item.setQuantity(item.quantity.value!! -1)
                holder.quantityTextView.text = item.quantity.value.toString()
            }

        }
    }

        /**
         * Return the size of your dataset (invoked by the layout manager)
         */
        override fun getItemCount(): Int = orderViewModel.dataset.size
    }