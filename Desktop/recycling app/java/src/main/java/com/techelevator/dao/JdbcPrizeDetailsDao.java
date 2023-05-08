package com.techelevator.dao;

import com.techelevator.model.PrizeDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPrizeDetailsDao implements PrizeDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDetailsDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}


    //Get current list of prizes
    @Override
    public List<PrizeDetails> getPrizeList() {
        List<PrizeDetails> allPrizes = new ArrayList<>();
        String sql = "SELECT prize_id, prize_name, point_value " +
                    "FROM prize_details;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            allPrizes.add(mapRowToPrizeDetail(results));
        }
        return allPrizes;
    }

    //Get prize by prize_id
    @Override
    public PrizeDetails getPrizeByPrizeId(int prizeId) {
        PrizeDetails prize = null;
        String sql = "SELECT prize_id, prize_name, point_value " +
                    "FROM prize_details " +
                    "WHERE prize_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, prizeId);
        if(result.next()) {
            prize = mapRowToPrizeDetail(result);
        }
        return prize;
    }

    //Get prize by prize_name
    @Override
    public PrizeDetails getPrizeByPrizeName(String prizeName) {
        PrizeDetails prize = null;
        String sql = "SELECT prize_id, prize_name, point_value " +
                    "FROM prize_details " +
                    "WHERE prize_name = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, prizeName);
        if(result.next()) {
            prize = mapRowToPrizeDetail(result);
        }
        return prize;
    }

    //Add a new prize to the list
    @Override
    public PrizeDetails addPrize(PrizeDetails newPrize) {
        String sql = "INSERT INTO prize_details (prize_name, point_value) " +
                    "VALUES (?, ?) RETURNING prize_id;";
        Integer prizeId = jdbcTemplate.queryForObject(sql, Integer.class, newPrize.getPrize_name(), newPrize.getPoint_value());

        return getPrizeByPrizeId(prizeId);
    }

    //Update a prize
    @Override
    public PrizeDetails updatePrize(PrizeDetails updatedPrize) {
        String sql = "UPDATE prize_details SET prize_name = ?, point_value = ? " +
                    "WHERE prize_id = ?;";
        jdbcTemplate.update(sql, updatedPrize.getPrize_name(), updatedPrize.getPoint_value(), updatedPrize.getPrize_id());

        return getPrizeByPrizeId(updatedPrize.getPrize_id());
    }

    //Delete a prize from the list
    @Override
    public void deletePrize(int prizeId) {
        String sql = "DELETE FROM prize_details " +
                    "WHERE prize_id = ?;";

        jdbcTemplate.update(sql, prizeId);
    }

    public PrizeDetails mapRowToPrizeDetail(SqlRowSet rs) {

        PrizeDetails prize = new PrizeDetails();

        prize.setPrize_id(rs.getInt("prize_id"));
        prize.setPrize_name(rs.getString("prize_name"));
        prize.setPoint_value(rs.getInt("point_value"));

        return prize;
    }
}
