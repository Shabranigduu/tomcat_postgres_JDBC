package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TicketEntity {
    private Long id;
    private String passengerNo;
    private String passengerName;
    private Long flightId;
    private String seatNo;
    private BigDecimal cost;



    public Long getId() {
        return this.id;
    }

    public String getPassengerNo() {
        return this.passengerNo;
    }

    public String getPassengerName() {
        return this.passengerName;
    }

    public Long getFlightId() {
        return this.flightId;
    }

    public String getSeatNo() {
        return this.seatNo;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassengerNo(String passengerNo) {
        this.passengerNo = passengerNo;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TicketEntity)) return false;
        final TicketEntity other = (TicketEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$passengerNo = this.getPassengerNo();
        final Object other$passengerNo = other.getPassengerNo();
        if (this$passengerNo == null ? other$passengerNo != null : !this$passengerNo.equals(other$passengerNo))
            return false;
        final Object this$passengerName = this.getPassengerName();
        final Object other$passengerName = other.getPassengerName();
        if (this$passengerName == null ? other$passengerName != null : !this$passengerName.equals(other$passengerName))
            return false;
        final Object this$flightId = this.getFlightId();
        final Object other$flightId = other.getFlightId();
        if (this$flightId == null ? other$flightId != null : !this$flightId.equals(other$flightId)) return false;
        final Object this$seatNo = this.getSeatNo();
        final Object other$seatNo = other.getSeatNo();
        if (this$seatNo == null ? other$seatNo != null : !this$seatNo.equals(other$seatNo)) return false;
        final Object this$cost = this.getCost();
        final Object other$cost = other.getCost();
        if (this$cost == null ? other$cost != null : !this$cost.equals(other$cost)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TicketEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $passengerNo = this.getPassengerNo();
        result = result * PRIME + ($passengerNo == null ? 43 : $passengerNo.hashCode());
        final Object $passengerName = this.getPassengerName();
        result = result * PRIME + ($passengerName == null ? 43 : $passengerName.hashCode());
        final Object $flightId = this.getFlightId();
        result = result * PRIME + ($flightId == null ? 43 : $flightId.hashCode());
        final Object $seatNo = this.getSeatNo();
        result = result * PRIME + ($seatNo == null ? 43 : $seatNo.hashCode());
        final Object $cost = this.getCost();
        result = result * PRIME + ($cost == null ? 43 : $cost.hashCode());
        return result;
    }

    public String toString() {
        return this.getId() + ": passengerNo=" + this.getPassengerNo() + ", passengerName=" + this.getPassengerName() + ", flightId=" + this.getFlightId() + ", seatNo=" + this.getSeatNo() + ", cost=" + this.getCost();
    }
}
