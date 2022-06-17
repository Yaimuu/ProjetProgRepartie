export class Learner {
  numUtil: number | undefined;
  surname: string | undefined;
  forename: string | undefined;

  constructor(data?: any) {
    if (data != null) {
      this.numUtil = data.numUtil;
      this.surname = data.surname;
      this.forename = data.forename;
    }
  }
}
