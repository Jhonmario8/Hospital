package com.hospital.modelo.dto;

public class SerieTemporalDto {

    private String periodo;
    private long total;

    public SerieTemporalDto(String periodo, long total) {
        this.periodo = periodo;
        this.total = total;
    }

    public SerieTemporalDto(String periodo, Long total) {
        this(periodo, total != null ? total : 0L);
    }

    public SerieTemporalDto(String periodo, Number total) {
        this(periodo, total != null ? total.longValue() : 0L);
    }

    public SerieTemporalDto() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}