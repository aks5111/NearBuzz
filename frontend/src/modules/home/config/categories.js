import {
  Compass,
  Dumbbell,
  PersonStanding,
  PartyPopper,
  HeartHandshake,
  Trophy,
  Ticket,
  ShoppingBag,
} from 'lucide-react';
import { fetchTravel } from '../../../api/travel.api';
import { fetchFitness } from '../../../api/fitness.api';
import { fetchCalisthenics } from '../../../api/calisthenics.api';
import { fetchPartyPlace } from '../../../api/partyplace.api';
import { fetchFriendsNearby } from '../../../api/friendsnearby.api';
import { fetchSportsActivity } from '../../../api/sportsactivity.api';
import { fetchEventBooking } from '../../../api/eventbooking.api';
import { fetchShoppingProducts } from '../../../api/shopping.api';

export const HOME_CATEGORIES = [
  { key: 'travel', label: 'Travel', path: '/travel', icon: Compass, fetchFn: () => fetchTravel() },
  { key: 'fitness', label: 'Fitness', path: '/fitness', icon: Dumbbell, fetchFn: () => fetchFitness() },
  {
    key: 'calisthenics',
    label: 'Calisthenics',
    path: '/calisthenics',
    icon: PersonStanding,
    fetchFn: () => fetchCalisthenics(),
  },
  {
    key: 'partyplace',
    label: 'Party Place',
    path: '/party-place',
    icon: PartyPopper,
    fetchFn: () => fetchPartyPlace(),
  },
  {
    key: 'friendsnearby',
    label: 'Meet Friends Nearby',
    path: '/friends-nearby',
    icon: HeartHandshake,
    fetchFn: () => fetchFriendsNearby(),
  },
  {
    key: 'sportsactivity',
    label: 'Sports Activity',
    path: '/sports-activity',
    icon: Trophy,
    fetchFn: () => fetchSportsActivity(),
  },
  {
    key: 'eventbooking',
    label: 'Event Booking',
    path: '/event-booking',
    icon: Ticket,
    fetchFn: () => fetchEventBooking(),
  },
  {
    key: 'shopping',
    label: 'Shopping',
    path: '/shopping',
    icon: ShoppingBag,
    fetchFn: () => fetchShoppingProducts(),
  },
];
