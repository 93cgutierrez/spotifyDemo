package co.demo.spotifydemo.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.Country;
import com.blongho.country_data.World;
import com.bumptech.glide.Glide;

import java.util.List;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.CountryListItemBinding;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolderCountry> {
    private static String TAG = CountryRecyclerAdapter.class.getCanonicalName();
    private List<String> countryList;
    private Context context;

    public CountryRecyclerAdapter(Context context,
                                  List<String> countryList) {
        this.context = context;
        this.countryList = countryList;

    }

    @NonNull
    @Override
    public CountryRecyclerAdapter.ViewHolderCountry onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        CountryListItemBinding binding =
                CountryListItemBinding.inflate(layoutInflater,
                        parent, false);
        return new CountryRecyclerAdapter.ViewHolderCountry(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerAdapter.ViewHolderCountry holder, int position) {
        holder.setDataCountry(context, countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class ViewHolderCountry extends RecyclerView.ViewHolder{
        private CountryListItemBinding binding;

        public ViewHolderCountry(@NonNull CountryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        @SuppressLint("SetTextI18n")
        public void setDataCountry(Context context, String country) {
            binding.tvCountryName.setText(country);
            try{
                final int flag = World.getFlagOf(country);
                Glide.with(context)
                        .load(flag)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(binding.ivCountryImage);
            } catch (Exception e) {
                binding.ivCountryImage.setImageResource(R.drawable.ic_launcher_background);
                Log.e(TAG, "setDataCountry: ", e);
            }


        }
    }
}



