package rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom

data class ChartData(
    val dateCreated: String,
    var count: Int
): Comparable<ChartData> {
    override fun compareTo(other: ChartData): Int {
        return dateCreated.compareTo(other.dateCreated)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChartData
        if (dateCreated != other.dateCreated) return false
        return true
    }

    override fun hashCode(): Int {
        return dateCreated.hashCode()
    }
}