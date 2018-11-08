import format from 'data-fns/format'
import parseDate from 'date-fnd/parse'

export default function formatDate(date) {
  date = parseDate(date)
  return format(date, 'DD-MMM-YYYY')
}
