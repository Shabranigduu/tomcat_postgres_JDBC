package dao;

import entity.TicketEntity;
import util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class TicketDao {
    private TicketDao() {
    }

    private static final TicketDao INSTANCE = new TicketDao();

    private static final String GET_BY_ID_SQL = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost
            FROM ticket
            WHERE id = ?
            """;

    private static final String CREATE_SQL = """
            INSERT INTO ticket(passenger_no, passenger_name, flight_id, seat_no, cost)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String GETALL_SQL = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost
            FROM ticket
            """;
    private static final String UPDATE = """
           update ticket
                       set passenger_no = ?,
                           passenger_name = ?,
                           flight_id = ?,
                           seat_no = ?,
                           cost = ?
                       WHERE id = ?
            """;

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public TicketEntity addNew(TicketEntity ticketEntity) throws SQLException {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ticketEntity.getPassengerNo());
            preparedStatement.setString(2, ticketEntity.getPassengerName());
            preparedStatement.setLong(3, ticketEntity.getFlightId());
            preparedStatement.setString(4, ticketEntity.getSeatNo());
            preparedStatement.setBigDecimal(5, ticketEntity.getCost());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticketEntity.setId(generatedKeys.getObject("id", Long.class));
            }
            return ticketEntity;
        }
    }

    public TicketEntity update(Long id, String passengerNo, String passengerName, Long flightId, String seatNo, BigDecimal cost) throws SQLException {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, passengerNo);
            preparedStatement.setString(2,passengerName);
            preparedStatement.setLong(3, flightId);
            preparedStatement.setString(4, seatNo);
            preparedStatement.setBigDecimal(5, cost);
            preparedStatement.setLong(6, id);

            if (preparedStatement.executeUpdate() > 0){
                TicketEntity ticketEntity = TicketEntity.builder()
                        .id(id)
                        .passengerNo(passengerNo)
                        .passengerName(passengerName)
                        .flightId(flightId)
                        .seatNo(seatNo)
                        .cost(cost)
                        .build();
                return ticketEntity;
            }else return null;
        }
    }

    public TicketEntity getById(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TicketEntity ticketEntity = new TicketEntity();
            while (resultSet.next()) {
                ticketEntity.setId(resultSet.getObject("id", Long.class));
                ticketEntity.setPassengerNo(resultSet.getObject("passenger_no", String.class));
                ticketEntity.setPassengerName(resultSet.getObject("passenger_name", String.class));
                ticketEntity.setFlightId(resultSet.getObject("flight_id", Long.class));
                ticketEntity.setSeatNo(resultSet.getObject("seat_no", String.class));
                ticketEntity.setCost(resultSet.getObject("cost", BigDecimal.class));

            }
            return ticketEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TicketEntity> getAll() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GETALL_SQL)) {
            List<TicketEntity> listOfTicket = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TicketEntity ticketEntity = TicketEntity.builder()
                        .id(resultSet.getObject("id", Long.class))
                        .passengerNo(resultSet.getObject("passenger_no", String.class))
                        .passengerName(resultSet.getObject("passenger_name", String.class))
                        .flightId(resultSet.getObject("flight_id", Long.class))
                        .seatNo(resultSet.getObject("seat_no", String.class))
                        .cost(resultSet.getObject("cost", BigDecimal.class))
                        .build();
                listOfTicket.add(ticketEntity);
            }
            return listOfTicket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
