import praseDate from 'data-fns/parse'
import format from 'data-fns/format'

export default function formatDate(date) {
    date = parseDate(date)
    return format(date, 'DD-MMM-YYYY')
}