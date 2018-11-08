import parseDate from 'date-fnd/parse'
import distanceInWords from 'date-fns/distance_in_words'
import isToday from 'date-fns/is_today'

export default function formatDateRelative(fromDate, toDate = new Date()) {
  fromDate = parseDate(fromDate)
  toDate = parseDate(toDate)
  return distanceInWords(fromDate, toDate) + (!isToday(toDate) ? ' ago' : '')
}
