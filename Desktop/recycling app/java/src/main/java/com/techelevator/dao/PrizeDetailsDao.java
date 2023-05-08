package com.techelevator.dao;

import com.techelevator.model.PrizeDetails;

import java.util.List;

public interface PrizeDetailsDao {

    List<PrizeDetails> getPrizeList();

    PrizeDetails getPrizeByPrizeId(int prizeId);

    PrizeDetails getPrizeByPrizeName(String prizeName);

    PrizeDetails addPrize(PrizeDetails newPrize);

    PrizeDetails updatePrize(PrizeDetails updatedPrize);

    void deletePrize(int prizeId);
}
