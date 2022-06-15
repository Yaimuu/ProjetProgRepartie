export class Learner {
  id: number | undefined;
  surname: string | undefined;
  forename: string | undefined;

  constructor(data?: any) {
    if (data != null) {
      this.id = data.id;
      this.surname = data.surname;
      this.forename = data.forename;
    }
  }
}
